<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Receipt</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/receipt.css">
</head>

<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="payment-success-layout">
                <div class="header-box">
                    <div class="box__row1-header">
                        <span class="text-big-title">Biên lai</span></div>
                    <div class="box__row2-header">
                        <div><span class="text-small-title">Biên nhận cho Giỏ hàng - 11 tháng 11, 2025</span></div>
                        <div>
                            <span class="status text-small-title">
                                <i class="fa-solid fa-circle-check icon-check" style="color: #018d4a;"></i>
                                Thành công</span></div>

                    </div>

                </div>
                <div class="grid summary-box1">
                    <div class="grid summary-box">
                        <div class="box__row1">
                            <span class="row1__id text">Mã đơn hàng:</span>
                            <span class="number">ORD101</span>
                        </div>
                        <div class="box__row2">
                            <span class="row2__time text">Ngày:</span>
                            <span class="number">11/11/2025</span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Giá gốc:</span>
                            <span class="number">2.796.000đ</span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Số tiền giảm:</span>
                            <span class="number">- 1.000.000đ</span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Tổng cộng:</span>
                            <span class="number">1.796.000đ</span>
                        </div>
                        <div class="box__row4">
                            <span class="row4__payment-method text">Phương thức thanh toán:</span>
                            <span class="number">Momo</span>
                        </div>


                    </div>

                </div>
                <div class="order-title">
                    <div class="title__2">
                        <div id="items">Hóa đơn chi tiết</div>
                        <div class=" price-header">
                            <span id="price">Giá</span>
                        </div>
                    </div>
                </div>
                <div class="scrollable-order-list">
                    <ul>
                        <li> <div class="grid__row-2">
                            <div class="order-items">
                                <div class="items__content">
                                    <div class="content__image">
                                        <img srcset="https://static.unica.vn/upload/images/2023/07/Screenshot%20(45).png_m_1690356655.jpg"
                                             alt="" class="image">
                                    </div>
                                    <div class="content__name text-paragraph">
                                        <p class="items__name ">Tư duy phản biện</p>
                                    </div>

                                </div>

                                <div class="items__price">
                                    <span class="amount-discounted ">399.000đ</span>
                                    <div><span class="amount-origin ">599.000đ</span></div>
                                </div>

                            </div>
                        </div></li>
                        <li> <div class="grid__row-2">
                            <div class="order-items">
                                <div class="items__content">
                                    <div class="content__image">
                                        <img srcset="https://tse1.mm.bing.net/th/id/OIP.-qNL8MTdeRuVGRqoTYXzTAHaEJ?cb=ucfimg2ucfimg=1&w=2000&h=1121&rs=1&pid=ImgDetMain&o=7&rm=3"
                                             alt="" class="image">
                                    </div>
                                    <div class="content__name text-paragraph">
                                        <p class="items__name ">Lãnh Đạo Không Cần Chức Danh</p>
                                    </div>

                                </div>

                                <div class="items__price">
                                    <div><span class="amount-discounted ">559.000đ</span></div>
                                    <div><span class="amount-origin ">899.000đ</span></div>

                                </div>

                            </div>
                        </div></li>
                        <li> <div class="grid__row-2">
                            <div class="order-items">
                                <div class="items__content">
                                    <div class="content__image">
                                        <img srcset="https://tse3.mm.bing.net/th/id/OIP.k5sDq201q3UKgMwrVdcyBQHaEK?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
                                             alt="" class="image">
                                    </div>
                                    <div class="content__name text-paragraph">
                                        <p class="items__name ">Kỹ năng giải quyết vấn đề cho hiệu quả</p>
                                    </div>

                                </div>

                                <div class="items__price">
                                    <div><span class="amount-discounted ">399.000đ</span></div>
                                    <div><span class="amount-origin ">599.000đ</span></div>
                                </div>

                            </div>
                        </div></li>
                        <li> <div class="grid__row-2">
                            <div class="order-items" style="border: none">
                                <div class="items__content">
                                    <div class="content__image">
                                        <img srcset="https://static.ybox.vn/2022/11/3/1669791323216-Thi%E1%BA%BFt%20k%E1%BA%BF%20ch%C6%B0a%20c%C3%B3%20t%C3%AAn%20(1).png"
                                             alt="" class="image">
                                    </div>
                                    <div class="content__name text-paragraph">
                                        <p class="items__name ">Networking Chuyên Nghiệp</p>
                                    </div>

                                </div>

                                <div class="items__price">
                                    <div><span class="amount-discounted ">399.000đ</span></div>
                                    <div><span class="amount-origin ">699.000đ</span></div>
                                </div>

                            </div>
                        </div></li>
                    </ul>


                </div>

                <div class="button-box ">
                    <a href="order-history.jsp" class="turn-page">
                        <div class="header__button button">
                            <button type="button" class="home-btn dark-button">Trở về</button>
                        </div>
                    </a>

                </div>
            </div>


        </div>

    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>
</body>
</html>