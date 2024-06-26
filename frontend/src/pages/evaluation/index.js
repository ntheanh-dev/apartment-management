import React, { useEffect, useState } from 'react';
import { authApi, endPoints } from '../../configs/APIs';
import {
    Alert,
    Backdrop,
    Button,
    CircularProgress,
    FormControl,
    FormControlLabel,
    FormLabel,
    Radio,
    RadioGroup,
    Snackbar,
    TextField,
    Typography,
} from '@mui/material';
import { Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

export default function Evaluation() {
    const [Criterion, setCriterion] = useState([]);
    const [Loading, setLoading] = useState(false);
    const [feedback, setfeedback] = useState();
    const [evaluation, setEvaluation] = useState([]);
    const [alertMessage, setAlertMessage] = useState('Đã có lỗi xảy ra');
    const [openAleart, setOpenAlert] = useState(false);
    const nav = useNavigate();
    useEffect(() => {
        setLoading(true);
        const loadCriterion = async () => {
            try {
                let res = await authApi().get(endPoints['getCriterion']);
                if (res.status == 400) {
                    setOpenAlert(true);
                } else {
                    setCriterion(res.data);
                }
            } catch (ex) {
                console.log(ex);
                setOpenAlert(true);
            } finally {
                setLoading(false);
            }
        };
        loadCriterion();
    }, []);
    const handleChange = (index, newValue) => {
        let array = [...evaluation];
        array[index] = newValue;
        setEvaluation(array);
    };
    const handleSubmitEvaluation = async () => {
        const obj = {
            evaluation: { feedback: feedback },
            detailEvaluations: [],
        };
        evaluation.map((e, idx) => {
            obj.detailEvaluations.push({
                idCriterion: idx + 1,
                rate: e,
            });
        });
        setLoading(true);
        try {
            var res = await authApi().post(endPoints['postEvaluation'], JSON.stringify(obj), {
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
            });
            if (res.status == 200) {
                nav('/');
            }
        } catch (ex) {
            setOpenAlert(true);
            console.log(ex);
        } finally {
            setLoading(false);
        }
    };
    return (
        <Container className="flex items-center gap-10 flex-col mt-10" component="main" maxWidth="xs">
            <Backdrop sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={Loading}>
                <CircularProgress color="inherit" />
            </Backdrop>
            <Typography component="h1" variant="h4">
                <b className="uppercase">Đánh giá chất lượng</b>
            </Typography>
            <FormControl className="flex gap-10" required>
                {Criterion == [] ? (
                    <></>
                ) : (
                    Criterion.map((c, idx) => {
                        return (
                            <div key={idx.toString()}>
                                <FormLabel id="demo-radio-buttons-group-label my-10">{c.name}</FormLabel>
                                <RadioGroup
                                    aria-labelledby="demo-radio-buttons-group-label"
                                    name={c.id}
                                    onChange={(e) => handleChange(idx, e.target.value)}
                                    className="flex flex-row gap-40"
                                >
                                    <FormControlLabel
                                        value="Không hài lòng"
                                        control={<Radio />}
                                        label="Không hài lòng"
                                    />
                                    <FormControlLabel value="Hài lòng" control={<Radio />} label="Hài lòng" />
                                    <FormControlLabel value="Rất hài lòng" control={<Radio />} label="Rất hài lòng" />
                                </RadioGroup>
                            </div>
                        );
                    })
                )}
                <TextField
                    sx={{ width: '100ch' }}
                    id="outlined-basic"
                    label="Ý kiến khác"
                    variant="outlined"
                    value={feedback}
                    onChange={(e) => setfeedback(e.target.value)}
                />
                <div className="flex justify-end">
                    <Button onClick={handleSubmitEvaluation} className="w-10" variant="contained">
                        Gửi
                    </Button>
                </div>
            </FormControl>
            <Snackbar
                open={openAleart}
                onClose={() => setOpenAlert(false)}
                autoHideDuration={6000}
                anchorOrigin={{ horizontal: 'right', vertical: 'top' }}
            >
                <Alert severity="error" va sx={{ width: '100%' }}>
                    {alertMessage}
                </Alert>
            </Snackbar>
        </Container>
    );
}
