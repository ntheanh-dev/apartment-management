import React, { useState } from 'react';
import Header from '../Header/index';
import MyDrawer from '../MyDrawer';
export const DefaultLayout = ({ children }) => {
    const [open, setOpen] = useState(false);
    const drawerWidth = 240;
    return (
        <>
            <Header open={open} setOpen={setOpen} />
            <MyDrawer open={open} setOpen={setOpen} drawerWidth={drawerWidth} />
            <div className="h-screen pt-16">{children}</div>
        </>
    );
};
