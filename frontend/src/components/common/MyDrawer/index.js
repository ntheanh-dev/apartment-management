import * as React from 'react';
import { styled, useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import { IoIosArrowBack } from 'react-icons/io';
import { IoIosArrowForward } from 'react-icons/io';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { IoHome } from 'react-icons/io5';
import { BiSolidCabinet } from 'react-icons/bi';
import { MdOutlineMessage } from 'react-icons/md';
import { Link } from 'react-router-dom';
import { MdFamilyRestroom } from 'react-icons/md';
const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
    justifyContent: 'flex-end',
}));

export default function MyDrawer({ open, setOpen, drawerWidth }) {
    const theme = useTheme();

    const handleDrawerClose = () => {
        setOpen(false);
    };

    const listDrawerItem = [
        {
            path: '/',
            text: 'Trang Chủ',
            icon: IoHome,
        },
        {
            path: '/chat',
            text: 'Tin Nhắn Nhóm',
            icon: MdOutlineMessage,
        },
        {
            path: '/cabinet',
            text: 'Tủ Đồ Online',
            icon: BiSolidCabinet,
        },
        {
            path: '/relatives',
            text: 'Người Thân',
            icon: MdFamilyRestroom,
        },
    ];

    return (
        <Box sx={{ display: 'flex', flexGrow: 1 }}>
            <CssBaseline />

            <Drawer
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                    },
                }}
                variant="persistent"
                anchor="left"
                open={open}
            >
                <DrawerHeader>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'ltr' ? <IoIosArrowBack /> : <IoIosArrowForward />}
                    </IconButton>
                </DrawerHeader>
                <Divider />
                <List>
                    {listDrawerItem.map((item) => (
                        <ListItem key={item.text} disablePadding onClick={handleDrawerClose}>
                            <ListItemButton>
                                <ListItemIcon>
                                    <item.icon />
                                </ListItemIcon>
                                <Link to={item.path}>
                                    <ListItemText primary={item.text} />
                                </Link>
                            </ListItemButton>
                        </ListItem>
                    ))}
                </List>
                <Divider />
            </Drawer>
        </Box>
    );
}
