import {
    Alert,
    Backdrop,
    Box,
    Breadcrumbs,
    Button,
    Checkbox,
    CircularProgress,
    Dialog,
    DialogActions,
    DialogTitle,
    IconButton,
    Link,
    Snackbar,
    Tooltip,
    Typography,
} from '@mui/material';
import React, { useEffect, useState } from 'react';
import { MdKeyboardArrowLeft } from 'react-icons/md';
import { BsArrowDown } from 'react-icons/bs';
import { visuallyHidden } from '@mui/utils';
import { MdFilterList } from 'react-icons/md';
import { FormControl, FormLabel, Option, Select, Sheet, Table } from '@mui/joy';
import { MdOutlineKeyboardArrowLeft, MdOutlineKeyboardArrowRight } from 'react-icons/md';
import { Link as LinkTo } from 'react-router-dom';
import { authApi, endPoints } from '../../configs/APIs';
import cookie from 'react-cookies';
import { Badge } from 'react-bootstrap';
import queryString from 'query-string';
import { MdOutlineCallReceived } from 'react-icons/md';
function labelDisplayedRows({ from, to, count }) {
    return `${from}–${to} of ${count !== -1 ? count : `more than ${to}`}`;
}

const headCells = [
    {
        id: 'name',
        ableSort: true,
        disablePadding: false,
        label: 'Tên',
    },
    {
        id: 'description',
        ableSort: false,
        disablePadding: false,
        label: 'Mô Tả',
    },
    {
        id: 'image',
        ableSort: false,
        disablePadding: false,
        label: 'Hình Ảnh',
    },
    {
        id: 'deliveryDate',
        ableSort: true,
        disablePadding: false,
        label: 'Ngày Giao Tới',
    },
    {
        id: 'receivedDate',
        ableSort: true,
        disablePadding: false,
        label: 'Ngày Nhận',
    },
    {
        id: 'status',
        ableSort: false,
        disablePadding: false,
        label: 'Trạng Thái',
    },
];

function EnhancedTableHead(props) {
    const { onSelectAllClick, order, orderBy, numSelected, size, rowCount, onRequestSort } = props;
    const createSortHandler = (property) => (event) => {
        onRequestSort(event, property);
    };
    return (
        <thead>
            <tr>
                <th>
                    <Checkbox
                        indeterminate={numSelected > 0 && numSelected < size}
                        checked={rowCount > 0 && numSelected === size}
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
                    const active = orderBy === headCell.id;
                    return (
                        <th
                            key={headCell.id}
                            aria-sort={active ? { asc: 'ascending', desc: 'descending' }[order] : undefined}
                        >
                            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                            <Link
                                underline="none"
                                color="neutral"
                                // textColor={active ? 'primary.plainColor' : undefined}
                                component="button"
                                onClick={createSortHandler(headCell.id)}
                                fontWeight="lg"
                                className="flex flex-row-reverse items-center space-x-4"
                                sx={{
                                    '& svg': {
                                        transition: '0.2s',
                                        transform: active && order === 'desc' ? 'rotate(0deg)' : 'rotate(180deg)',
                                    },
                                    '&:hover': { '& svg': { opacity: 1 } },
                                }}
                            >
                                {active && <BsArrowDown />}
                                {headCell.label}
                                {active ? (
                                    <Box component="span" sx={visuallyHidden}>
                                        {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                                    </Box>
                                ) : null}
                            </Link>
                        </th>
                    );
                })}
            </tr>
        </thead>
    );
}
function EnhancedTableToolbar(props) {
    const { numSelected, setOpen } = props;

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
                    {numSelected} selected
                </Typography>
            ) : (
                <Typography level="body-lg" sx={{ flex: '1 1 100%' }} id="tableTitle" component="div">
                    Món Hàng
                </Typography>
            )}

            {numSelected > 0 ? (
                <Tooltip title="Nhận">
                    <IconButton size="sm" color="danger" variant="solid" onClick={() => setOpen(true)}>
                        <MdOutlineCallReceived />
                    </IconButton>
                </Tooltip>
            ) : (
                <Tooltip title="Filter list">
                    <IconButton size="sm" variant="outlined" color="neutral">
                        <MdFilterList />
                    </IconButton>
                </Tooltip>
            )}
        </Box>
    );
}
const Cabinet = () => {
    const [order, setOrder] = React.useState('asc');
    const [orderBy, setOrderBy] = React.useState('');
    const [selected, setSelected] = React.useState([]);
    const [page, setPage] = React.useState(1);
    const [size, setSize] = React.useState(5);

    const [data, setData] = useState([]);
    const [total, setTotal] = useState(0);

    const [loading, setLoading] = useState(false);

    const [refresh, setRefresh] = useState(false);

    //--------------Dialog--------------
    const [open, setOpen] = React.useState(false);
    //---------------Alert---------------
    const [alertMessage, setAlertMessage] = useState('Đã Có Lỗi Xảy Ra');
    const [openAleart, setOpenAlert] = useState(false);
    const handleClose = () => {
        setOpen(false);
    };

    const handleReceiveItem = () => {
        setOpen(false);
        const fetchApi = async () => {
            setLoading(true);
            const formData = new FormData();
            formData.append('items', selected);
            try {
                await authApi().post(endPoints['postItems'], JSON.stringify({ items: selected }), {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    },
                });
                setPage(1);
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
        const param = queryString.stringify(
            {
                sortBy: orderBy,
                order: order,
                page: page,
                size: size,
            },
            { skipEmptyString: true },
        );
        const loadItems = async () => {
            try {
                let res = await authApi().get(endPoints['items'](`?${param}`), {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    },
                });
                console.log(res.data);
                setData(res.data.result?.results);
                setTotal(res.data.result.count);
            } catch (ex) {
                console.log(ex);
                setOpenAlert(true);
            } finally {
                setLoading(false);
            }
        };
        loadItems();
    }, [page, size, orderBy, order, refresh]);

    const handleRequestSort = (event, property) => {
        console.log(property);
        if (headCells.find((e) => e.id === property && e.ableSort)) {
            const isAsc = orderBy === property && order === 'asc';
            setOrder(isAsc ? 'desc' : 'asc');
            setOrderBy(property);
            setPage(1);
        }
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
        const selectedItems = data.find((e) => e.id === id);
        if (selectedItems.receivedDate !== null) {
            return;
        }
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

    const handleChangePage = (newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event, newValue) => {
        setSize(parseInt(newValue.toString(), 10));
        setPage(1);
    };

    const getLabelDisplayedRowsTo = () => {
        if (total === -1) {
            return page * size;
        }
        return size === -1 ? total : Math.min(total, page * size);
    };

    const isSelected = (id) => selected.indexOf(id) !== -1;

    const ableSelectItemCount = data.filter((i) => i.receivedDate == null).length;

    // Avoid a layout jump when reaching the last page with empty rows.
    const emptyRows = data.length < size ? size - data.length : 0;

    const breadcrums = [
        {
            path: '/',
            text: 'Trang Chủ',
        },
        {
            path: '/cabinet',
            text: 'Tủ Đồ',
        },
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

            <Sheet variant="outlined" sx={{ width: '100%', boxShadow: 'sm', borderRadius: 'sm' }}>
                <EnhancedTableToolbar numSelected={selected.length} setOpen={setOpen} />
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
                        size={ableSelectItemCount}
                        order={order}
                        orderBy={orderBy}
                        onSelectAllClick={handleSelectAllClick}
                        onRequestSort={handleRequestSort}
                        rowCount={total}
                    />
                    <tbody>
                        {data.map((row, index) => {
                            const isItemSelected = isSelected(row.id);

                            const labelId = `enhanced-table-checkbox-${row.id}`;
                            const deliveryDate = row.deliveryDate.join('-');
                            const receivedDate = row?.receivedDate?.join('-');
                            return (
                                <tr
                                    onClick={(event) => handleClick(event, row.id)}
                                    role="checkbox"
                                    aria-checked={isItemSelected}
                                    tabIndex={-1}
                                    key={row.id}
                                    // selected={isItemSelected}
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
                                            disabled={row.receivedDate !== null}
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
                                    <td>{row.description}</td>
                                    <td>
                                        <div className="w-20 h-20 mx-auto overflow-hidden">
                                            <img className="w-full h-full object-cover" src={row.image} alt="" />
                                        </div>
                                    </td>
                                    <td>{deliveryDate}</td>
                                    <td>{receivedDate}</td>
                                    <td>
                                        {row.receivedDate ? (
                                            <Badge bg="success">Đã Nhận</Badge>
                                        ) : (
                                            <Badge bg="warning">Chưa Nhận</Badge>
                                        )}
                                    </td>
                                </tr>
                            );
                        })}
                        {emptyRows > 0 && (
                            <tr
                                style={{
                                    height: `calc(${emptyRows} * 80px)`,
                                    '--TableRow-hoverBackground': 'transparent',
                                }}
                            >
                                <td colSpan={7} aria-hidden />
                            </tr>
                        )}
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colSpan={7}>
                                <Box
                                    sx={{
                                        display: 'flex',
                                        alignItems: 'center',
                                        gap: 2,
                                        justifyContent: 'flex-end',
                                    }}
                                >
                                    <FormControl orientation="horizontal" size="sm">
                                        <FormLabel>Số Hàng Hiện Thị:</FormLabel>
                                        <Select onChange={handleChangeRowsPerPage} value={size}>
                                            <Option value={5}>5</Option>
                                            <Option value={10}>10</Option>
                                            <Option value={25}>25</Option>
                                        </Select>
                                    </FormControl>
                                    <Typography textAlign="center" sx={{ minWidth: 80 }}>
                                        {labelDisplayedRows({
                                            from: total === 0 ? 0 : (page - 1) * size + 1,
                                            to: getLabelDisplayedRowsTo(),
                                            count: total === -1 ? -1 : total,
                                        })}
                                    </Typography>
                                    <Box sx={{ display: 'flex', gap: 1 }}>
                                        <IconButton
                                            size="sm"
                                            color="neutral"
                                            variant="outlined"
                                            disabled={page === 1}
                                            onClick={() => handleChangePage(page - 1)}
                                            sx={{ bgcolor: 'background.surface' }}
                                        >
                                            <MdOutlineKeyboardArrowLeft />
                                        </IconButton>
                                        <IconButton
                                            size="sm"
                                            color="neutral"
                                            variant="outlined"
                                            disabled={total !== 0 ? page >= Math.ceil(total / size) : false}
                                            onClick={() => handleChangePage(page + 1)}
                                            sx={{ bgcolor: 'background.surface' }}
                                        >
                                            <MdOutlineKeyboardArrowRight />
                                        </IconButton>
                                    </Box>
                                </Box>
                            </td>
                        </tr>
                    </tfoot>
                </Table>
            </Sheet>

            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">{'Bạn có muốn nhận những món hàng này ?'}</DialogTitle>
                {/* <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        Bạn có muốn nhận những món hàng này?
                    </DialogContentText>
                </DialogContent> */}
                <DialogActions>
                    <Button onClick={handleClose}>Từ Chối</Button>
                    <Button onClick={handleReceiveItem} autoFocus>
                        Đồng Ý
                    </Button>
                </DialogActions>
            </Dialog>

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

export default Cabinet;
