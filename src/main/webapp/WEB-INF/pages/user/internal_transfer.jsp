<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chuyển khoản nội ngân hàng</title>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon/banking.webp" />
        <!-- Bootstrap CSS -->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="<c:url value="/resources/style/fontawesome-free-6.2.0-web/css/all.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/fragment/header.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/fragment/header_responsive.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/fragment/footer.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/fragment/footer_responsive.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/css/internal_transfer.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/responsive/internal_transfer_responsive.css" />" />
    </head>
    <body>
        <jsp:include page="fragment/header.jsp" />
        <div class="container-fluid content">
            <div class="row">
                <div class="col">
                    <img src="${pageContext.request.contextPath}/resources/images/banner/banner_transfer.jpg" class="img-fluid" alt="" />
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="#"><i class="fa-solid fa-house-chimney"></i> Trang chủ</a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">
                                <i class="fa-solid fa-money-bill-transfer"></i> Chuyển tiền: nội
                                ngân hàng
                            </li>
                        </ol>
                    </nav>
                </div>
            </div>
            <h3 class="title-comm"><span class="title-holder">Chuyển tiền nội bộ ngân hàng</span></h3>
            <div class="container contentForm mt-5">
                <c:if test="${confirm == false}">
                    <div class="row transactionContent justify-content-center">
                        <div class="col-8">
                            <h5 class="text-center">Thông tin chuyển tiền nội ngân hàng</h5>
                            <form action="${pageContext.request.contextPath}/resultInternalTransfer" method="POST">
                                <div class="row destinationAccount">
                                    <div class="col-4">
                                        <img src="${pageContext.request.contextPath}/resources/images/cardblack_TanTuBank.png" class="img-fluid" alt="">
                                    </div>
                                    <div class="col-8">
                                        <b>${sessionScope.bankAccount.accountNumber}</b>
                                        <p>Số thẻ <span style="font-weight: bolder;">TanTuBank</span> - Black Card</p>
                                        <p>Số dư khả dụng: <fmt:formatNumber type="number" value="${sessionScope.bankAccount.balance}" />đ</p>
                                    </div>
                                </div>
                                <div class="form-group mt-3">
                                    <label for="receiveAccount">Nhập số tài khoản nhận (TK Thanh toán): </label>
                                    <input type="text" class="form-control" name="receiveAccount" id="receiveAccount"
                                           placeholder="Số tài khoản này phải là tài khoản TanTuBank" onchange="this.form.submit()" value="${receiveAccount}">
                                    <small class="form-text text-muted">${messageAccountNumber}</small>
                                </div>
                                <div class="form-group mt-3">
                                    <label>Tên tài khoản nhận: </label>
                                    <input type="text" class="form-control" value="${bankReceiveAccount.accountName}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="balance">Nhập số tiền: </label>
                                    <input type="number" class="form-control" name="balance" id="balance"
                                           placeholder="Số tiền cần chuyển là bao nhiêu?">
                                    <small class="form-text text-muted">Help text</small>
                                </div>
                                <div class="row captcha">
                                    <div class="col-8">
                                        <div class="form-group">
                                            <label for="captcha">Nhập captcha (6 ký tự cả số và chữ): </label>
                                            <input type="text" class="form-control" name="captcha" id="captcha" placeholder="Captcha nè!">
                                            <small class="form-text text-muted">Help text</small>
                                        </div>
                                    </div>
                                    <div class="col-3">
                                        <p>Abc123</p>
                                    </div>
                                    <div class="col-1">
                                        <button class="btn"><i class="fa-solid fa-rotate"></i></button>
                                    </div>
                                </div>
                                <div class="row nextButton text-center">
                                    <div class="col">
                                        <button class="btn">
                                            <i class="fa-solid fa-forward"></i> Tiếp tục</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${confirm == true}">
                    <div class="row makeTransaction mt-2">
                        <form action="" method="POST">
                            <div class="col-6 displayInfoTransaction">
                                <h5 class="text-center" >Thông tin giao dịch</h5>
                                <div class="row destinationAccount">
                                    <div class="col-6">
                                        <img src="${pageContext.request.contextPath}/resources/images/cardblack_TanTuBank.png" class="img-fluid" alt="">
                                    </div>
                                    <div class="col-6">
                                        <b>0000 0000 0000 0000</b>
                                        <p>Số thẻ TanTuBank - Black Card</p>
                                        <p>Số dư khả dụng: 10000đ</p>
                                    </div>
                                </div>
                                <div class="form-group receiveAccount mt-3">
                                    <label for="accountNumber">Số tài khoản nhận: <span>0101 0000 0101 0000</span></label>
                                </div>
                                <div class="form-group nameReceiveAccount mt-3">
                                    <label>Tên tài khoản nhận: <span>Hồ Ngọc Tấn</span></label>
                                </div>
                                <div class="form-group balance">
                                    <label for="balance">Số tiền chuyển: <span>1.000.000đ</span></label>
                                </div>
                            </div>
                            <div class="col-6 confirmCode">
                                <h5 class="text-center">Xác nhận</h5>
                                <div class="row justify-content-center">
                                    <div class="col-3">
                                        <img src="${pageContext.request.contextPath}/resources/images/icon/gmail.png" class="img-fluid" alt="">
                                    </div>
                                    <div class="col-2 mt-5">
                                        <img src="${pageContext.request.contextPath}/resources/images/icon/arrow-right.png" class="img-fluid" alt="">
                                    </div>
                                    <div class="col-6">
                                        <img src="${pageContext.request.contextPath}/resources/images/banner/internal_transfer.jpg" class="img-fluid" alt="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="balance">Nhập mã xác nhận (Hiệu lực mã xác nhận có giá trị 1 phút ): <span id="time"
                                                                                                                           >01:00</span> minutes!</label>
                                    <input type="text" class="form-control" name="balance" id="balance"
                                           placeholder="Mã xác nhận đã được gửi tới email của bạn!">
                                    <small class="form-text text-muted">Help text</small>
                                    <script>
                                        function startTimer(duration, display) {
                                            var timer = duration,
                                                    minutes, seconds;
                                            setInterval(function () {
                                                minutes = parseInt(timer / 60, 10);
                                                seconds = parseInt(timer % 60, 10);

                                                minutes = minutes < 10 ? "0" + minutes : minutes;
                                                seconds = seconds < 10 ? "0" + seconds : seconds;

                                                display.textContent = minutes + ":" + seconds;

                                                if (--timer < 0) {
                                                    timer = duration;
                                                    // location.href='index.html'
                                                }
                                            }, 1000);
                                        }

                                        window.onload = function () {
                                            var oneMinutes = 60,
                                                    display = document.querySelector('#time');
                                            startTimer(oneMinutes, display);
                                        };
                                    </script>
                                </div>
                                <div class="row transferButton text-center">
                                    <div class="col">
                                        <button class="btn"><i
                                                class="fa-solid fa-money-bill-transfer"></i> Chuyển tiền</button>
                                    </div>
                                </div>
                            </div>
                        </form>


                    </div>
                </c:if>

            </div>
        </div>
        <jsp:include page="fragment/footer.jsp" />
        <jsp:include page="fragment/go_to_top.jsp" />
    </body>
</html>
