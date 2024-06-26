import {
    Backdrop,
    CircularProgress,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    IconButton,
    Typography,
} from '@mui/material';
import React, { useEffect, useRef, useState } from 'react';
import { Button } from 'react-bootstrap';
import { BsThreeDotsVertical } from 'react-icons/bs';
import { CiImageOn } from 'react-icons/ci';
import { Message } from './Message';
import { useSelector } from 'react-redux';
import {
    collection,
    doc,
    getDocs,
    onSnapshot,
    orderBy,
    query,
    serverTimestamp,
    setDoc,
    where,
} from 'firebase/firestore';
import { db } from '../../firebase/config';
import { useNavigate } from 'react-router-dom';
const Chat = () => {
    const navigate = useNavigate();
    const { data } = useSelector((state) => state.user);
    if (!data.username) {
        navigate('/login');
    }
    const [openAlert, setOpenAlert] = useState(false);
    const [alertMessage, setAlertMessage] = useState('Đã có lỗi xảy ra');
    const [loading, setLoading] = useState(false);
    const [messages, setMessages] = useState([]);
    const [roomId, setRoomId] = useState('');
    const [text, setText] = useState('');
    const bodyRef = useRef();
    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const collection_ref = collection(db, 'users');
                const q = query(collection_ref, where('username', '==', data.username));
                const docRefs = await getDocs(q);
                let res = [];
                docRefs.forEach((doc) => res.push({ ...doc.data() }));
                if (res.length === 0) {
                    throw new Error('Bạn chưa được thêm vào nhóm chat');
                } else {
                    res = [];
                    const collection_ref = collection(db, 'rooms');
                    const q = query(collection_ref, where('members', 'array-contains', data.username));
                    const docRefs = await getDocs(q);
                    docRefs.forEach((doc) => res.push({ ...doc.data() }));
                    if (res.length === 0) {
                        throw new Error('Không tìm thấy dữ liệu');
                    } else {
                        setRoomId(res[0].id);
                        const q = query(
                            collection(db, 'messages'),
                            where('roomId', '==', res[0].id),
                            orderBy('createAt'),
                        );
                        onSnapshot(q, (querySnapshot) => {
                            querySnapshot.docChanges().forEach((change) => {
                                if (change.type === 'added') {
                                    setMessages((mess) => [
                                        ...mess,
                                        {
                                            ...change.doc.data(),
                                        },
                                    ]);
                                }
                            });
                        });
                    }
                }
            } catch (ex) {
                setAlertMessage(ex.message);
                setOpenAlert(true);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, []);

    const handleClose = () => {
        setOpenAlert(false);
        navigate('/');
    };

    const scroolToBootom = () => {
        if (bodyRef.current) {
            bodyRef.current.scrollTo({
                top: bodyRef.current.scrollHeight,
                behavior: 'smooth',
            });
        }
    };

    const handleSentMessage = async () => {
        const savedDoc = doc(collection(db, 'messages'));
        await setDoc(savedDoc, {
            text: text,
            senderUsername: data.username,
            createAt: serverTimestamp(),
            roomId: roomId,
            id: savedDoc.id,
        });
        setText('');
        scroolToBootom();
    };

    return (
        <div className="w-full h-full flex flex-col">
            <div className="flex flex-row justify-between items-center py-2 px-6 border-blue-600 border-b-2">
                <Typography variant="h6" noWrap component="div">
                    Nhóm Chat Chung
                </Typography>
                <IconButton color="inherit" aria-label="open drawer" edge="start">
                    <BsThreeDotsVertical />
                </IconButton>
            </div>
            <div ref={bodyRef} className="flex-1 px-6 py-2 overflow-y-auto scroll-smooth flex flex-col gap-4">
                {messages.map((mess, index) => {
                    return <Message key={index} mess={mess} />;
                })}
            </div>
            <div className="flex flex-row h-16 items-center px-6 border-blue-600 border-t-2">
                <input
                    className="w-100 border-none outline-none"
                    type="text"
                    placeholder="Gửi tin nhắn đến nhóm của bạn..."
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                />
                <div className="flex flex-row items-center space-x-2">
                    <input type="file" hidden={true} id="image" />
                    <label htmlFor="image" className="cursor-hover ">
                        <IconButton color="inherit" aria-label="open drawer" edge="start">
                            <CiImageOn />
                        </IconButton>
                    </label>
                    <Button onClick={handleSentMessage}>Gửi</Button>
                </div>
            </div>

            <Backdrop sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={loading}>
                <CircularProgress color="inherit" />
            </Backdrop>

            <Dialog open={openAlert} onClose={handleClose} aria-labelledby="responsive-dialog-title">
                <DialogTitle id="responsive-dialog-title">{alertMessage}</DialogTitle>
                <DialogContent>
                    <DialogContentText>Quay trở lại trang chủ?</DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} autoFocus>
                        Ok
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default Chat;
