import axios from 'axios';
import cookie from 'react-cookies';
export const BASE_URL = '/ApartmentManagement/'; 
// export const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8085/ApartmentManagement/';

export const endPoints = {
    items: (param) => `api/items/${param}`,
    postItems: 'api/items/',
    relatives: 'api/relatives/',
    addRelative: 'api/relatives/add/',
    login: 'api/token/',
    myInfo: 'api/residents/my-info/',
    changeAvatar: 'api//residents/change-avatar/',
    changePassWord: 'api/change-password/',
    getCriterion: 'api/evaluation/',
    postEvaluation: 'api/evaluation/',
    postReport: 'api/report/',
    getBill: 'api/bill/',
};

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            Authorization: cookie.load('token'),
        },
    });
};

export default axios.create({
    baseURL: BASE_URL,
});
