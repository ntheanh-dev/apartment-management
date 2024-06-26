import { Avatar, Tooltip } from '@mui/material';
import { collection, getDocs, query, where } from 'firebase/firestore';
import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { db } from '../../firebase/config';
import { useNavigate } from 'react-router-dom';

export const Message = ({ mess }) => {
    const { text, senderUsername, createAt } = mess;
    const nav = useNavigate();
    const { data } = useSelector((state) => state.user);
    if (!data.username) {
        nav('/login');
    }

    const onwer = data.username === senderUsername;
    const [userDoc, setUserDoc] = useState({});
    useEffect(() => {
        const fetchData = async () => {
            const collection_ref = collection(db, 'users');
            const q = query(collection_ref, where('username', '==', senderUsername));
            const docRefs = await getDocs(q);
            let res = [];
            docRefs.forEach((doc) => res.push({ ...doc.data() }));
            if (res.length > 0) {
                setUserDoc({ ...res[0] });
            }
        };
        //Nếu là onwer thì lấy dữ liệu trong redux thay vì fetch tren firebase
        if (onwer) {
            setUserDoc(data);
        } else {
            fetchData();
        }
    }, []);
    return (
        <div className={`flex ${onwer ? 'flex-row-reverse' : 'flex-row'} flex-row gap-2`}>
            <div className={`flex flex-col ${onwer && 'items-end'}`}>
                <span className="font-bold text-[#00aff0]">{senderUsername}</span>
                <Avatar src={userDoc?.avatar} />
            </div>
            <div className={`flex flex-col gap-2 ${onwer && 'items-end'} mt-4`}>
                <Tooltip title={createAt && timeAgo(createAt)} placement="top">
                    <span
                        className={` px-3 py-2 ${
                            onwer ? 'rounded-l-lg' : 'rounded-r-lg'
                        } inline-block max-w-[60vw] break-words  rounded-b-lg w-fit border border-[#d9f4ff] bg-[#d9f4ff] text-black `}
                    >
                        {text}
                    </span>
                </Tooltip>
                {/* <img
                    className={`w-1/2   rounded-b-lg ${onwer ? 'rounded-l-lg' : 'rounded-r-lg'}`}
                    src="https://images.unsplash.com/photo-1502657877623-f66bf489d236?auto=format&fit=crop&w=800"
                    srcSet="https://images.unsplash.com/photo-1502657877623-f66bf489d236?auto=format&fit=crop&w=800&dpr=2 2x"
                    loading="lazy"
                    alt=""
                /> */}
            </div>
        </div>
    );
};
const timeAgo = (timestamp) => {
    const createdAt = new Date(timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000);
    const now = Date.now();
    const timeDiff = now - createdAt.getTime();

    // Convert milliseconds to seconds
    const seconds = Math.floor(timeDiff / 1000);

    // Logic to determine time ago message
    if (seconds < 60) {
        return `${seconds}s trước`;
    } else if (seconds < 3600) {
        const minutes = Math.floor(seconds / 60);
        return `${minutes} phút trước`;
    } else if (seconds < 86400) {
        const hours = Math.floor(seconds / 3600);
        return `${hours} giờ trước`;
    } else {
        const days = Math.floor(seconds / 86400);
        return `${days} ngày trước`;
    }
};
