import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { DefaultLayout } from './components/common/Layout/DefaultLayout';
import { publicRoutes } from './routes';
import Login from './pages/auth/Login';
import RequiredAuth from './pages/auth/RequiredAuth';
function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<Login />} />
                {publicRoutes.map((route, index) => {
                    const Page = route.component;
                    return (
                        <Route
                            key={index}
                            path={route.path}
                            element={
                                <RequiredAuth>
                                    <DefaultLayout>
                                        <Page />
                                    </DefaultLayout>
                                </RequiredAuth>
                            }
                        />
                    );
                })}
            </Routes>
        </BrowserRouter>
    );
}

export default App;
