import { Alert, Backdrop, Button, CircularProgress, FormControl, Snackbar, TextField, Typography } from '@mui/material';
import { Container } from 'react-bootstrap';
import React, { useState } from 'react';
import { Textarea } from '@mui/joy';
import { authApi, endPoints } from '../../configs/APIs';
import { useNavigate } from 'react-router-dom';

export default function Report() {
    const [title, settitle] = useState('');
    const [content, setcontent] = useState('');
    const [Loading, setLoading] = useState(false);
    const [alertMessage, setAlertMessage] = useState('Đã có lỗi xảy ra');
    const [openAleart, setOpenAlert] = useState(false);
    const [returnhome,setReturnhome] = useState(false);
    const nav = useNavigate();
    const handleSubmit = async () => {
        const obj = {
            title:title,
            content:content
        }
        try {
            var res = await authApi().post(endPoints['postReport'], JSON.stringify(obj), {
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
            });
            if(res.status === 200){
                setReturnhome(true);
            }
        } catch (ex) {
            setOpenAlert(true);
            console.log(ex);
        } finally {
            setLoading(false);
            if(returnhome){
                nav("/");
            }
        }
    }
    return (
        <Container className="flex items-center gap-10 flex-col mt-10" component="main" maxWidth="xs">
            <Backdrop sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={Loading}>
                <CircularProgress color="inherit" />
            </Backdrop>
            <Typography component="h1" variant="h4">
                <b className="uppercase">Đóng  góp</b>
            </Typography>
            <FormControl className="flex gap-10" required>
                <TextField
                    sx={{ width: '100ch' }}
                    id="outlined-basic"
                    label="Tiêu đề"
                    variant="outlined"
                    value={title}
                    onChange={(e) => settitle(e.target.value)}
                />
                <Textarea color="neutral" value={content} onChange={(e) =>setcontent(e.target.value)} minRows={`10`} placeholder="Nội dung" size="lg" variant="outlined" />

                <div className="flex justify-end">
                    <Button onClick={handleSubmit} className="w-10" variant="contained">
                        Gửi
                    </Button>
                </div>
            </FormControl>
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
        </Container>
    );
}
