import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import slide1 from '../../asset/images/1.png';
import slide2 from '../../asset/images/2.png';
import { Grid, Link, Typography } from '@mui/material';
import { AiTwotoneNotification } from 'react-icons/ai';
import icon1 from '../../asset/images/iocn3.png';
import icon2 from '../../asset/images/icon1.png';
import icon3 from '../../asset/images/icon2.png';
import icon4 from '../../asset/images/icon4.png';
import { Link as LinkTo } from 'react-router-dom';
const Home = () => {
    var settings = {
        infinite: true,
        slidesToShow: 1,
        autoplay: true,
        autoplaySpeed: 5000,
    };
    return (
        <div className="p-6">
            <div className="flex justify-between items-center">
                <Typography variant="h6" noWrap component="div" className="flex items-center">
                    <AiTwotoneNotification />
                    Thông báo từ BQL
                </Typography>
                <Link href="#">Xem thêm</Link>
            </div>
            <Slider {...settings} className="h-1/2 overflow-hidden">
                <div>
                    <img src={slide1} alt="slide" />
                </div>
                <div>
                    <img src={slide2} alt="slide" />
                </div>
            </Slider>
            <Typography variant="h6" noWrap component="div" className="flex items-center">
                <span className="font-semibold text-lg text-blue-900 my-2">Tiện Ích</span>
            </Typography>
            <Grid container spacing={{ xs: 1 }}>
                <Grid item sm={12} xs={12} md={2} className="flex justify-center items-center">
                    <LinkTo to={'/bill'}>
                        <div className="cursor-pointer hover:opacity-80 border-1 border-gray-500 rounded-lg w-48 h-48 flex justify-center items-center drop-shadow-md">
                            <div className="flex flex-col justify-center items-center">
                                <div>
                                    <img src={icon1} />
                                </div>
                                <Typography variant="h2" noWrap component="div" className="flex items-center">
                                    <span className="font-semibold text-lg text-black">Hoá đơn</span>
                                </Typography>
                            </div>
                        </div>
                    </LinkTo>
                </Grid>
                <Grid item sm={12} xs={12} md={2}>
                    <LinkTo to={'/relatives'}>
                        <div className="cursor-pointer hover:opacity-80 border-1 border-gray-500 rounded-lg w-48 h-48 flex justify-center items-center drop-shadow-md">
                            <div className="flex flex-col justify-center items-center">
                                <div>
                                    <img src={icon2} />
                                </div>
                                <Typography variant="h2" noWrap component="div" className="flex items-center">
                                    <span className="font-semibold text-lg text-black">Người Thân</span>
                                </Typography>
                            </div>
                        </div>
                    </LinkTo>
                </Grid>
                <Grid item sm={12} xs={12} md={2}>
                    <LinkTo to={'/chat'}>
                        <div className="cursor-pointer hover:opacity-80 border-1 border-gray-500 rounded-lg w-48 h-48 flex justify-center items-center drop-shadow-md">
                            <div className="flex flex-col justify-center items-center">
                                <div>
                                    <img src={icon3} />
                                </div>
                                <Typography variant="h2" noWrap component="div" className="flex items-center">
                                    <span className="font-semibold text-lg text-black">Tin Nhắn Chung</span>
                                </Typography>
                            </div>
                        </div>
                    </LinkTo>
                </Grid>
                <Grid item sm={12} xs={12} md={2}>
                    <LinkTo to={'/evaluation'}>
                        <div className="cursor-pointer hover:opacity-80 border-1 border-gray-500 rounded-lg w-48 h-48 flex justify-center items-center drop-shadow-md">
                            <div className="flex flex-col justify-center items-center">
                                <div>
                                    <img src={icon4} />
                                </div>
                                <Typography variant="h2" noWrap component="div" className="flex items-center">
                                    <span className="font-semibold text-lg text-black">Đánh Giá/Phản Hồi</span>
                                </Typography>
                            </div>
                        </div>
                    </LinkTo>
                </Grid>
                <Grid item sm={12} xs={12} md={2} className="flex justify-center items-center">
                    <LinkTo to={'/evaluation'}>
                        <div className="cursor-pointer hover:opacity-80 border-1 border-gray-500 rounded-lg w-48 h-48 flex justify-center items-center drop-shadow-md">
                            <div className="flex flex-col justify-center items-center">
                                <div>
                                    <img src={icon1} />
                                </div>
                                <Typography variant="h2" noWrap component="div" className="flex items-center">
                                    <span className="font-semibold text-lg text-black">Đánh giá</span>
                                </Typography>
                            </div>
                        </div>
                    </LinkTo>
                </Grid>
                <Grid item sm={12} xs={12} md={2}>
                    <LinkTo to={'/report'}>
                        <div className="cursor-pointer hover:opacity-80 border-1 border-gray-500 rounded-lg w-48 h-48 flex justify-center items-center drop-shadow-md">
                            <div className="flex flex-col justify-center items-center">
                                <div>
                                    <img src={icon1} />
                                </div>
                                <Typography variant="h2" noWrap component="div" className="flex items-center">
                                    <span className="font-semibold text-lg text-black">Đóng góp</span>
                                </Typography>
                            </div>
                        </div>
                    </LinkTo>
                </Grid>
            </Grid>
        </div>
    );
};

export default Home;
