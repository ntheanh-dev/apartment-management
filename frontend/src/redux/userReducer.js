import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import APIs, { authApi, endPoints } from '../configs/APIs';
import cookie from 'react-cookies';

const INIT_USER_STATE = {
    status: 'idle',
    data: {},
};

export const userReducer = createSlice({
    name: 'user',
    initialState: INIT_USER_STATE,
    reducers: {
        setUserAfterLogout: (state) => {
            cookie.remove('token');
            state.user = INIT_USER_STATE;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(login.pending, (state) => {
                state.status = 'pending';
            })
            .addCase(login.fulfilled, (state, action) => {
                state.status = 'idle';
                if (action.payload) {
                    state.data = action.payload;
                }
            })
            .addCase(login.rejected, (state) => {
                state.status = 'rejected';
            })

            .addCase(changeAvatar.pending, (state) => {
                state.status = 'pending';
            })
            .addCase(changeAvatar.fulfilled, (state, action) => {
                state.status = 'idle';
                if (action.payload) {
                    state.data.avatar = action.payload;
                }
            })
            .addCase(changeAvatar.rejected, (state) => {
                state.status = 'rejected';
            });
    },
});

export const login = createAsyncThunk('user/fetchUserData', async (user, { rejectWithValue }) => {
    try {
        let res = await APIs.post(endPoints['login'], JSON.stringify(user), {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            },
        });
        cookie.save('token', res.data?.result?.token);
        let u = await authApi().get(endPoints['myInfo']);
        return {
            ...u.data.result,
            username: user.username,
        };
    } catch (err) {
        console.log(err);
        return rejectWithValue(err.response.data);
    }
});

export const changeAvatar = createAsyncThunk('user/changeAvatar', async (form, { rejectWithValue }) => {
    try {
        let res = await authApi().post(endPoints['changeAvatar'], form, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        return res.data.result;
    } catch (err) {
        return rejectWithValue(err.response.data);
    }
});
export const { setUserAfterLogout } = userReducer.actions;
export default userReducer.reducer;
