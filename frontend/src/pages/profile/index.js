import { Typography } from '@mui/joy';
import FormControl from '@mui/joy/FormControl';
import FormLabel from '@mui/joy/FormLabel';
import Input from '@mui/joy/Input';
import {
    Alert,
    Avatar,
    Button,
    Dialog,
    DialogActions,
    DialogTitle,
    DialogContentText,
    Grid,
    Snackbar,
    TextField,
    Backdrop,
    CircularProgress,
    IconButton,
} from '@mui/material';
import React, { useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { authApi, endPoints } from '../../configs/APIs';
import { changeAvatar } from '../../redux/userReducer';
import { unwrapResult } from '@reduxjs/toolkit';

const Profile = () => {
    //--------------Dialog--------------
    const [openDialog, setOpenDialog] = useState(false);
    //---------------Alert---------------
    const [alertMessage, setAlertMessage] = useState('Đã Có Lỗi Xảy Ra');
    const [alertType, setAlertType] = useState('error');
    const [openAleart, setOpenAlert] = useState(false);
    const [loading, setLoading] = useState(false);
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const { data } = useSelector((state) => state.user);

    const dispatch = useDispatch();

    const file = useRef();

    const handleChangePassword = () => {
        setLoading(true);
        setOpenDialog(false);
        const fetchApi = async () => {
            try {
                var res = await authApi().post(
                    endPoints['changePassWord'],
                    JSON.stringify({
                        oldPassword: oldPassword,
                        newPassword: newPassword,
                    }),
                    {
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8',
                        },
                    },
                );
                setAlertMessage('Đổi Mật Khẩu Thành Công');
                setAlertType('success');
                setOpenAlert(true);
            } catch (ex) {
                if (ex.response.data.code === 1003) {
                    setAlertMessage('Mật Khẩu Sai');
                    setAlertType('error');
                    setOpenAlert(true);
                    setOpenDialog(true);
                } else {
                    setAlertMessage('Đã Có Lỗi Xảy Ra');
                    setAlertType('error');
                    setOpenAlert(true);
                    setOpenDialog(true);
                }
            } finally {
                setLoading(false);
            }
        };
        fetchApi();
    };

    const handleChangeFile = (e) => {
        const form = new FormData();
        form.append('file', e.target.files[0]);
        setLoading(true);
        dispatch(changeAvatar(form))
            .then(unwrapResult)
            .then((e) => {
                setAlertMessage('Đổi Avatar Thành Công');
                setAlertType('success');
                setOpenAlert(true);
                setLoading(false);
            })
            .catch((e) => {
                if (e.code === 1009) {
                    setAlertMessage('Không thể tải ảnh lên cloud');
                } else {
                    setAlertMessage('Đã Có Lỗi Xảy Ra');
                }
                setAlertType('error');
                setOpenAlert(true);
                setLoading(false);
            });
    };

    return (
        <div className="p-6 flex flex-col gap-4">
            <div className="flex justify-between items-center">
                <div className="flex items-center gap-3">
                    <div>
                        <input
                            onChange={(e) => handleChangeFile(e)}
                            onClick={(e) => (e.target.value = null)}
                            ref={file}
                            hidden
                            type="file"
                            accept=".png,.jpg"
                            id="avatar"
                        />
                        <label htmlFor="avatar" className="cursor-pointer">
                            <Avatar alt="A" src={data.avatar} sx={{ width: 80, height: 80 }} />
                        </label>
                    </div>
                    <div className="flex flex-col">
                        <Typography level="h3">Nguyễn Thế Anh</Typography>
                        {data.email && <Typography level="body-sm">{data.email}</Typography>}
                    </div>
                </div>
                <div>
                    <Button variant="contained">Sửa</Button>
                </div>
            </div>
            <Grid container rowSpacing={2} columnSpacing={5}>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Họ tên</FormLabel>
                        <Input disabled value={data.fullName} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Ngày Sinh</FormLabel>
                        <Input disabled value={data.dateOfBirth} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Giới Tính</FormLabel>
                        <Input disabled value={data.gender ? 'Nam' : 'Nữ'} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>SĐT</FormLabel>
                        <Input disabled value={data.phone} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Quận/Huyện</FormLabel>
                        <Input disabled value={data.ward} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Đường</FormLabel>
                        <Input disabled value={data.address} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Số</FormLabel>
                        <Input disabled value={data.numberPlate} />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormLabel>Thành Phố</FormLabel>
                        <Input disabled value={data.city} />
                    </FormControl>
                </Grid>
            </Grid>
            <div className="flex justify-start">
                <Button onClick={() => setOpenDialog(true)} variant="outlined">
                    Đổi Mật Khẩu
                </Button>
            </div>

            <Backdrop sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={loading}>
                <CircularProgress color="inherit" />
            </Backdrop>

            <Dialog open={openDialog} aria-labelledby="draggable-dialog-title">
                <DialogTitle id="draggable-dialog-title">Đổi Mật Khẩu</DialogTitle>
                <DialogContentText>
                    <DialogContentText>
                        <div className="pt-2 px-4">
                            <Grid rowSpacing={2} columnSpacing={2}>
                                <Grid item xs={12}>
                                    <TextField
                                        sx={{ width: '30ch' }}
                                        label="Mật Khẩu Cũ"
                                        variant="outlined"
                                        value={oldPassword}
                                        type="password"
                                        onChange={(e) => setOldPassword(e.target.value)}
                                    />
                                </Grid>
                                <Grid item xs={12} className="mt-2">
                                    <TextField
                                        sx={{ width: '30ch' }}
                                        label="Mật Khẩu Mới"
                                        variant="outlined"
                                        value={newPassword}
                                        type="password"
                                        onChange={(e) => setNewPassword(e.target.value)}
                                    />
                                </Grid>
                            </Grid>
                        </div>
                    </DialogContentText>
                </DialogContentText>
                <DialogActions>
                    <Button onClick={() => setOpenDialog(false)}>Huỷ</Button>
                    <Button autoFocus onClick={() => handleChangePassword()}>
                        Đổi
                    </Button>
                </DialogActions>
            </Dialog>

            <Snackbar
                open={openAleart}
                onClose={() => setOpenAlert(false)}
                autoHideDuration={6000}
                anchorOrigin={{ horizontal: 'right', vertical: 'top' }}
            >
                <Alert severity={alertType} va sx={{ width: '100%' }}>
                    {alertMessage}
                </Alert>
            </Snackbar>
        </div>
    );
};

export default Profile;
