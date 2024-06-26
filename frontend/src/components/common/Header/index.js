import React from 'react';
import { CgProfile } from 'react-icons/cg';
import { CiLogout } from 'react-icons/ci';
import { Link } from 'react-router-dom';
import { Alert, Avatar, Box, Toolbar } from '@mui/material';
import { IoNotificationsSharp } from 'react-icons/io5';
import { Badge, Menu, MenuItem } from '@mui/material';
import { MdOutlineMessage } from 'react-icons/md';
import { AiOutlineMenu } from 'react-icons/ai';
import Typography from '@mui/material/Typography';
import MuiAppBar from '@mui/material/AppBar';
import IconButton from '@mui/material/IconButton';
import { styled } from '@mui/material/styles';
import { useSelector } from 'react-redux';
import { db } from '../../../firebase/config';
import { collection, getDocs, onSnapshot, orderBy, query, where } from 'firebase/firestore';
import PopupState, { bindTrigger, bindMenu } from 'material-ui-popup-state';

const Header = ({ open, setOpen, drawerWidth }) => {
    const handleDrawerOpen = () => {
        setOpen(true);
    };
    const { data } = useSelector((state) => state.user);
    const [notifications, setNotifications] = React.useState([]);

    React.useEffect(() => {
        const fetchData = async () => {
            try {
                const q = query(collection(db, 'notifications'), where('userId', '==', data.id), orderBy('createAt'));
                onSnapshot(q, (querySnapshot) => {
                    querySnapshot.docChanges().forEach((change) => {
                        if (change.type === 'added') {
                            console.log(change.doc.data());
                            setNotifications((noti) => [
                                ...noti,
                                {
                                    ...change.doc.data(),
                                },
                            ]);
                        }
                    });
                });
            } catch (ex) {
                console.log(ex);
            }
        };
        if (data.id) {
            fetchData();
        }
    }, []);

    const AppBar = styled(MuiAppBar, {
        shouldForwardProp: (prop) => prop !== 'open',
    })(({ theme, open }) => ({
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        ...(open && {
            width: `calc(100% - ${drawerWidth}px)`,
            marginLeft: `${drawerWidth}px`,
            transition: theme.transitions.create(['margin', 'width'], {
                easing: theme.transitions.easing.easeOut,
                duration: theme.transitions.duration.enteringScreen,
            }),
        }),
    }));

    return (
        <>
            <AppBar position="fixed" className="h-16">
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                        sx={{ mr: 2, ...(open && { display: 'none' }) }}
                    >
                        <AiOutlineMenu />
                    </IconButton>
                    <Typography variant="h6" noWrap component="div">
                        Xin chào, <span className="text-lg font-semibold">{data.fullName}</span>
                    </Typography>
                    <Box sx={{ flexGrow: 1 }} />
                    <IconButton size="large" aria-label="show 4 new mails" color="inherit">
                        <Link to={'/chat'}>
                            <Badge badgeContent={1} color="error">
                                <MdOutlineMessage />
                            </Badge>
                        </Link>
                    </IconButton>
                    <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
                        <PopupState variant="popover" popupId="demo-popup-menu">
                            {(popupState) => (
                                <React.Fragment>
                                    <IconButton
                                        size="large"
                                        aria-label="show 17 new notifications"
                                        color="inherit"
                                        {...bindTrigger(popupState)}
                                    >
                                        <Badge
                                            badgeContent={notifications.length > 0 ? notifications.length : undefined}
                                            color="error"
                                        >
                                            <IoNotificationsSharp />
                                        </Badge>
                                    </IconButton>
                                    <Menu {...bindMenu(popupState)}>
                                        {notifications.map((noti) => (
                                            <MenuItem onClick={popupState.close}>
                                                <Link to={'/bill'}>
                                                    <Alert variant="outlined" severity="warning">
                                                        {noti.notificationType === 'MONTHLY_BILL' &&
                                                            'Bạn có một hoá đơn cần thanh toán'}
                                                    </Alert>
                                                </Link>
                                            </MenuItem>
                                        ))}
                                    </Menu>
                                </React.Fragment>
                            )}
                        </PopupState>

                        <PopupState variant="popover" popupId="demo-popup-menu">
                            {(popupState) => (
                                <React.Fragment>
                                    <IconButton color="inherit" {...bindTrigger(popupState)} size="large">
                                        <Avatar src={data.avatar} alt="A" />
                                    </IconButton>
                                    <Menu {...bindMenu(popupState)}>
                                        <MenuItem>
                                            {' '}
                                            <Link
                                                to="/profile"
                                                className="flex items-center gap-1.5 text-sm font-medium duration-300 ease-in-out hover:text-primary lg:text-base"
                                            >
                                                <CgProfile />
                                                Trang Cá Nhấn
                                            </Link>
                                        </MenuItem>
                                        <MenuItem>
                                            <Link
                                                to="/login"
                                                className="flex items-center gap-1.5 text-sm font-medium duration-300 ease-in-out hover:text-primary lg:text-base"
                                            >
                                                <CiLogout />
                                                Đăng Xuất
                                            </Link>
                                        </MenuItem>
                                    </Menu>
                                </React.Fragment>
                            )}
                        </PopupState>
                    </Box>
                </Toolbar>
            </AppBar>
        </>
    );
};

export default Header;
