import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Navigate, useNavigate } from 'react-router-dom';

const RequiredAuth = ({ children }) => {
    const { data } = useSelector((state) => state.user);

    return data.username ? children : <Navigate to="/login" />;
};

export default RequiredAuth;
