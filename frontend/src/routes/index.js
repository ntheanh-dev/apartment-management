import Login from '../pages/auth/Login';
import Bill from '../pages/bill';
import Cabinet from '../pages/cabinet';
import Chat from '../pages/chat';
import Evaluation from '../pages/evaluation';
import Home from '../pages/home/Home';
import Profile from '../pages/profile';
import Relative from '../pages/relatives';
import Report from '../pages/report';

const publicRoutes = [
    // { path: '/login', component: Login, isNotDefault: true },
    { path: '/', component: Home },
    { path: '/chat', component: Chat },
    { path: '/cabinet', component: Cabinet },
    { path: '/relatives', component: Relative },
    { path: '/profile', component: Profile },
    { path: '/evaluation', component: Evaluation },
    { path: '/report', component: Report },
    { path: '/bill', component: Bill },
];

export { publicRoutes };
