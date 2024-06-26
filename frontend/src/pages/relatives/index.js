import {
    Alert,
    Backdrop,
    Breadcrumbs,
    Checkbox,
    CircularProgress,
    Snackbar,
    Link,
    Box,
    Typography,
    Tooltip,
    IconButton,
    Dialog,
    DialogTitle,
    DialogActions,
    Button,
    DialogContent,
    DialogContentText,
    Paper,
    Grid,
    TextField,
    Select,
    MenuItem,
    FormControl,
    InputLabel,
} from '@mui/material';
import React, { useEffect, useState } from 'react';
import { MdKeyboardArrowLeft } from 'react-icons/md';
import { Link as LinkTo } from 'react-router-dom';
import { authApi, endPoints } from '../../configs/APIs';
import { IoIosPersonAdd } from 'react-icons/io';
import { RiDeleteBin6Fill } from 'react-icons/ri';
import { Sheet } from '@mui/joy';
import { Table } from 'react-bootstrap';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs from 'dayjs';
const headCells = [
    {
        id: 'name',
        ableSort: false,
        disablePadding: false,
        label: 'Tên',
    },
    {
        id: 'dob',
        ableSort: false,
        disablePadding: false,
        label: 'Tuổi',
    },
    {
        id: 'relationshipType',
        ableSort: true,
        disablePadding: false,
        label: 'Quan Hệ',
    },
    {
        id: 'createdAt',
        ableSort: false,
        disablePadding: false,
        label: 'Ngày Thêm',
    },
];

function EnhancedTableHead(props) {
    const { onSelectAllClick, numSelected, size } = props;

    return (
        <thead>
            <tr>
                <th>
                    <Checkbox
                        indeterminate={numSelected > 0 && numSelected < size}
                        checked={numSelected > 0 && numSelected === size}
                        onChange={onSelectAllClick}
                        slotProps={{
                            input: {
                                'aria-label': 'select all desserts',
                            },
                        }}
                        sx={{ verticalAlign: 'sub' }}
                    />
                </th>
                {headCells.map((headCell) => {
                    return (
                        <th key={headCell.id}>
                            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                            <Link
                                underline="none"
                                color="neutral"
                                // textColor={active ? 'primary.plainColor' : undefined}
                                component="button"
                                fontWeight="lg"
                                className="flex flex-row-reverse items-center space-x-4"
                            >
                                {headCell.label}
                            </Link>
                        </th>
                    );
                })}
            </tr>
        </thead>
    );
}
function EnhancedTableToolbar(props) {
    const { numSelected, setOpen, setOpenAddRelativeDialog } = props;

    return (
        <Box
            sx={{
                display: 'flex',
                alignItems: 'center',
                py: 1,
                pl: { sm: 2 },
                pr: { xs: 1, sm: 1 },
                ...(numSelected > 0 && {
                    bgcolor: 'background.level1',
                }),
                borderTopLeftRadius: 'var(--unstable_actionRadius)',
                borderTopRightRadius: 'var(--unstable_actionRadius)',
            }}
        >
            {numSelected > 0 ? (
                <Typography sx={{ flex: '1 1 100%' }} component="div">
                    {numSelected} người được chọn
                </Typography>
            ) : (
                <Typography level="body-lg" sx={{ flex: '1 1 100%' }} id="tableTitle" component="div">
                    Người Thân Của Bạn
                </Typography>
            )}

            {numSelected > 0 ? (
                <Tooltip title="Nhận">
                    <IconButton size="sm" color="danger" variant="solid" onClick={() => setOpen(true)}>
                        <RiDeleteBin6Fill />
                    </IconButton>
                </Tooltip>
            ) : (
                <Tooltip title="Filter list">
                    <IconButton
                        size="sm"
                        variant="outlined"
                        color="neutral"
                        onClick={() => setOpenAddRelativeDialog(true)}
                    >
                        <IoIosPersonAdd />
                    </IconButton>
                </Tooltip>
            )}
        </Box>
    );
}
const Relative = () => {
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState([]);
    const [selected, setSelected] = useState([]);

    //--------------Dialog--------------
    const [open, setOpen] = React.useState(false);
    const [openAddRelativeDialog, setOpenAddRelativeDialog] = useState(false);
    //---------------Alert---------------
    const [alertMessage, setAlertMessage] = useState('Đã Có Lỗi Xảy Ra');
    const [openAleart, setOpenAlert] = useState(false);

    const [refresh, setRefresh] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };
    const handleSelectAllClick = (event) => {
        if (event.target.checked) {
            const newSelected = data.filter((i) => i.receivedDate == null).map((n) => n.id);
            setSelected(newSelected);
            return;
        }
        setSelected([]);
    };

    const handleClick = (event, id) => {
        const selectedIndex = selected.indexOf(id);
        let newSelected = [];

        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selected, id);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selected.slice(1));
        } else if (selectedIndex === selected.length - 1) {
            newSelected = newSelected.concat(selected.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(selected.slice(0, selectedIndex), selected.slice(selectedIndex + 1));
        }

        setSelected(newSelected);
    };

    const isSelected = (id) => selected.indexOf(id) !== -1;

    const handleRemoveRelatives = () => {
        setOpen(false);
        const fetchApi = async () => {
            setLoading(true);
            // const formData = new FormData();s
            // formData.append('items', selected);
            try {
                await authApi().post(endPoints['relatives'], JSON.stringify({ id: selected }), {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    },
                });
                setRefresh(!refresh);
                setSelected([]);
            } catch (ex) {
                setOpenAlert(true);
                console.log(ex);
            } finally {
                setLoading(false);
            }
        };
        fetchApi();
    };

    useEffect(() => {
        setLoading(true);

        const loadItems = async () => {
            try {
                let res = await authApi().get(endPoints['relatives']);
                setData(res.data.result);
            } catch (ex) {
                console.log(ex);
                setOpenAlert(true);
            } finally {
                setLoading(false);
            }
        };
        loadItems();
    }, [refresh]);

    const breadcrums = [
        {
            path: '/',
            text: 'Trang Chủ',
        },
        {
            path: '/relatives',
            text: 'Người Thân',
        },
    ];

    const formatDate = (dob) => {
        const y = dob.$y;
        const m = dob.$M > 0 && dob.$M < 10 ? `0${dob.$M}` : dob.$M;
        const d = dob.$D > 0 && dob.$D < 10 ? `0${dob.$D}` : dob.$D;

        return `${y}-${m}-${d}`;
    };

    const handleAddRelatives = () => {
        const newRelative = {
            name: `${lastName} ${firstName}`,
            dob: formatDate(dob),
            relationshipType: relationType,
        };
        setOpenAddRelativeDialog(false);
        setLoading(true);
        const fetchApi = async () => {
            try {
                var res = await authApi().post(endPoints['addRelative'], JSON.stringify(newRelative), {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    },
                });
                console.log(res.data);
                setData([res.data.result, ...data]);
            } catch (ex) {
                setOpenAlert(true);
                console.log(ex);
            } finally {
                setLoading(false);
            }
        };
        fetchApi();
    };

    const [relationType, setRelationType] = useState('');
    const [dob, setDob] = useState('2000-1-1');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    const handleSelectChange = (event) => {
        setRelationType(event.target.value);
    };

    const relationShipType = [
        'Cô',
        'Gì',
        'Chú',
        'Bác',
        'Anh',
        'Chị',
        'Em',
        'Cháu',
        'Cậu',
        'Mợ',
        'Ông',
        'Bà',
        'Bố',
        'Mẹ',
        'Khác',
    ];

    return (
        <div className="p-6 flex flex-col gap-3">
            <Backdrop sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={loading}>
                <CircularProgress color="inherit" />
            </Backdrop>

            <Breadcrumbs separator={<MdKeyboardArrowLeft />} aria-label="breadcrumbs">
                {breadcrums.map((item) => (
                    <LinkTo key={item.path} to={item.path} color="neutral" href="#separators">
                        {item.text}
                    </LinkTo>
                ))}
            </Breadcrumbs>

            {/* ------------------Remove Relative Confirm Dialog--------------- */}
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">{'Bạn có muốn huỷ đăng ký?'}</DialogTitle>
                <DialogActions>
                    <Button onClick={handleClose}>Từ Chối</Button>
                    <Button onClick={handleRemoveRelatives} autoFocus>
                        Đồng Ý
                    </Button>
                </DialogActions>
            </Dialog>
            {/* ------------------Add Relative Confirm Dialog--------------- */}
            <Dialog open={openAddRelativeDialog} aria-labelledby="draggable-dialog-title">
                <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
                    Đăng Ký Người Thân
                </DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        <div className="pt-2">
                            <Grid container rowSpacing={2} columnSpacing={2}>
                                <Grid item xs={6}>
                                    <TextField
                                        sx={{ width: '30ch' }}
                                        id="outlined-basic"
                                        label="Họ"
                                        variant="outlined"
                                        value={lastName}
                                        onChange={(e) => setLastName(e.target.value)}
                                    />
                                </Grid>
                                <Grid item xs={6}>
                                    <TextField
                                        sx={{ width: '30ch' }}
                                        id="outlined-basic"
                                        label="Tên"
                                        variant="outlined"
                                        value={firstName}
                                        onChange={(e) => setFirstName(e.target.value)}
                                    />
                                </Grid>

                                <Grid item xs={6}>
                                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                                        <DemoContainer components={['DatePicker']}>
                                            <DatePicker label="Ngày Sinh" onChange={(newValue) => setDob(newValue)} />
                                        </DemoContainer>
                                    </LocalizationProvider>
                                </Grid>
                                <Grid item xs={6}>
                                    <FormControl sx={{ width: '30ch', marginTop: '1ch' }}>
                                        <InputLabel id="demo-simple-select-label">Quan Hệ</InputLabel>
                                        <Select
                                            labelId="demo-simple-select-label"
                                            id="demo-simple-select"
                                            value={relationType}
                                            label="Quan Hệ"
                                            onChange={handleSelectChange}
                                        >
                                            {relationShipType.map((r) => (
                                                <MenuItem key={r} value={r}>
                                                    {r}
                                                </MenuItem>
                                            ))}
                                        </Select>
                                    </FormControl>
                                </Grid>
                            </Grid>
                        </div>
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpenAddRelativeDialog(false)}>Huỷ</Button>
                    <Button autoFocus onClick={handleAddRelatives}>
                        Thêm
                    </Button>
                </DialogActions>
            </Dialog>
            <Sheet variant="outlined" sx={{ width: '100%', boxShadow: 'sm', borderRadius: 'sm' }}>
                <EnhancedTableToolbar
                    numSelected={selected.length}
                    setOpen={setOpen}
                    setOpenAddRelativeDialog={setOpenAddRelativeDialog}
                />
                <Table
                    aria-labelledby="tableTitle"
                    hoverRow
                    sx={{
                        '--TableCell-headBackground': 'transparent',
                        '--TableCell-selectedBackground': (theme) => theme.vars.palette.success.softBg,
                        '& thead th:nth-of-type(1)': {
                            width: '40px',
                        },
                        '& thead th:nth-of-type(2)': {
                            width: '30%',
                        },
                        '& tr > *:nth-of-type(n+3)': { textAlign: 'left' },
                    }}
                >
                    <EnhancedTableHead
                        numSelected={selected.length}
                        size={data.length}
                        onSelectAllClick={handleSelectAllClick}
                    />
                    <tbody>
                        {data.map((row, index) => {
                            const isItemSelected = isSelected(row.id);
                            const labelId = `enhanced-table-checkbox-${row.id}`;
                            const createdAt = row?.createdAt?.join('-');
                            const currentYear = new Date().getFullYear();
                            const age = currentYear - row.dob[0];
                            return (
                                <tr
                                    onClick={(event) => handleClick(event, row.id)}
                                    role="checkbox"
                                    aria-checked={isItemSelected}
                                    tabIndex={-1}
                                    key={row.id}
                                    selected={isItemSelected}
                                    style={
                                        isItemSelected
                                            ? {
                                                  '--TableCell-dataBackground': 'var(--TableCell-selectedBackground)',
                                                  '--TableCell-headBackground': 'var(--TableCell-selectedBackground)',
                                              }
                                            : {}
                                    }
                                >
                                    <th scope="row">
                                        <Checkbox
                                            checked={isItemSelected}
                                            slotProps={{
                                                input: {
                                                    'aria-labelledby': labelId,
                                                },
                                            }}
                                            sx={{ verticalAlign: 'top' }}
                                        />
                                    </th>
                                    <th id={labelId} scope="row">
                                        {row.name}
                                    </th>
                                    <td>{age}</td>
                                    <td>{row.relationshipType}</td>
                                    <td>{createdAt}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </Table>
            </Sheet>

            <Snackbar
                open={openAleart}
                onClose={() => setOpenAlert(false)}
                autoHideDuration={6000}
                anchorOrigin={{ horizontal: 'right', vertical: 'top' }}
            >
                <Alert severity="error" va sx={{ width: '100%' }}>
                    {alertMessage}
                </Alert>
            </Snackbar>
        </div>
    );
};

export default Relative;
