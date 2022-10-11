<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
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
        <script src="<c:url value="/webjars/bootstrap/4.6.1/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>"></script>
    </head>
    <body>
        <jsp:include page="fragment/header.jsp" />
        <c:url var="home" value="/" scope="request" />
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
                <c:if test="${prepareIT == true}">
                    <div class="row transactionContent justify-content-center">
                        <div class="col-8">
                            <h5 class="text-center">Thông tin chuyển tiền nội ngân hàng</h5>
                            <mvc:form action="${pageContext.request.contextPath}/checkEnterReceiveAccount" 
                                      method="POST">
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
                                           placeholder="Số tài khoản này phải là tài khoản TanTuBank" onchange="this.form.submit()" 
                                           value="${sessionScope.receiveAccount}">
                                    <small class="form-text text-muted">${messageReceiveAccountNumber}</small>
                                </div>
                                <div class="form-group mt-3">
                                    <label>Tên tài khoản nhận: </label>
                                    <input type="text" class="form-control" value="${sessionScope.receiveBankAccount.accountName}" readonly>
                                </div>
                            </mvc:form>
                            <mvc:form action="${pageContext.request.contextPath}/resultPrepareInternalTransfer" method="POST">
                                <div class="form-group">
                                    <label for="balanceTransfer">Nhập số tiền: </label>
                                    <input type="text" min="0"  class="form-control" 
                                           name="balanceTransfer"  id="balanceTransfer"
                                           placeholder="Số tiền cần chuyển là bao nhiêu?" 
                                           value="${sessionScope.balanceTransaction}">
                                    <small class="form-text text-muted">${messageBalanceTransaction}</small>
                                    <script>
                                        var Amount = document.getElementById("balanceTransfer");
                                        Amount.addEventListener('keyup', function (evt) {
                                            var n = parseFloat(this.value.replace(/\D/g, ''), 10);
                                            Amount.value = n.toLocaleString();
                                        }, false);
                                    </script>
                                </div>
                                <div class="form-group">
                                    <label for="contentTransfer">Nội dung chuyển khoản: </label>
                                    <textarea class="form-control" name="contentTransfer" id="contentTransfer" rows="3"
                                              ><c:if test="${contentTransaction == ''}">${sessionScope.bankAccount.accountName} chuyển khoản</c:if>${contentTransaction}</textarea>
                                    <small class="form-text text-muted">${messageContentTransfer}</small>
                                </div>
                                <div class="row captcha">
                                    <div class="col-8">
                                        <div class="form-group">
                                            <label for="captcha">Nhập captcha (6 ký tự): </label>
                                            <input type="text" class="form-control" name="captcha" id="captcha" placeholder="Captcha nè!">
                                            <small class="form-text text-muted">${messageCaptcha}</small>
                                        </div>
                                    </div>
                                    <div class="col-3">
                                        <p id="displayCaptcha">${sessionScope.captcha}</p>
                                    </div>
                                    <div class="col-1">
                                        <p class="btn" onclick="change();"><i class="fa-solid fa-rotate"></i></p>
                                    </div>
                                </div>
                                <div class="row nextButton text-center">
                                    <div class="col">
                                        <button  class="btn">
                                            <i class="fa-solid fa-forward"></i> Tiếp tục</button>
                                    </div>
                                </div>
                            </mvc:form>


                            <script>
                                function change() {
                                    $.ajax({
                                        type: 'GET',
                                        contentType: "application/json",
                                        dataType: 'json',
                                        url: '${home}changeCapt',
                                        success: function (e) {
                                        },
                                        error: function (e) {
                                            $("#displayCaptcha").load("${home}changeCapt");
                                        }

                                    });
                                }

                            </script>
                        </div>
                    </div>
                </c:if>
                <c:if test="${makeIT == true}">
                    <mvc:form action="${pageContext.request.contextPath}/resultMakeInternalTransfer" method="POST">
                        <div class="row makeTransaction mt-2">
                            <div class="col-6 displayInfoTransaction">
                                <h5 class="text-center" >Thông tin giao dịch</h5>
                                <div class="row destinationAccount">
                                    <div class="col-6">
                                        <img src="${pageContext.request.contextPath}/resources/images/cardblack_TanTuBank.png" class="img-fluid" alt="">
                                    </div>
                                    <div class="col-6">
                                        <b>${bankAccount.accountNumber}</b>
                                        <p>Số thẻ TanTuBank - Black Card</p>
                                        <p>Số dư khả dụng: <fmt:formatNumber type="number" value="${sessionScope.bankAccount.balance}"/>đ</p>
                                    </div>
                                </div>
                                <div class="form-group receiveAccount mt-3">
                                    <label for="accountNumber">Số tài khoản nhận: <span>${sessionScope.receiveBankAccount.accountNumber}</span></label>
                                </div>
                                <div class="form-group nameReceiveAccount mt-3">
                                    <label>Tên tài khoản nhận: <span>${sessionScope.receiveBankAccount.accountName}</span></label>
                                </div>
                                <div class="form-group balance">
                                    <label for="balance">Số tiền chuyển: <span><fmt:formatNumber type="number" value="${sessionScope.balanceTransaction}" />đ</span></label>
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
                                    <label for="confirmCode">Nhập mã xác nhận (Hiệu lực mã xác nhận có giá trị 1 phút ): 
                                        <span id="time">01:00</span> minutes!</label>
                                    <input type="text" class="form-control" name="confirmCode" id="confirmCode"
                                           placeholder="Mã xác nhận đã được gửi tới email của bạn!">
                                    <small class="form-text text-muted">${messageConfirmCode}</small>
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
                                                    location.href = '${home}viewInternalTransfer'
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
                        </div>
                    </mvc:form>
                </c:if>
                <c:if test="${completeIT == true}">
                    <div class="row completeIT justify-content-center">
                        <div class="col-8 mt-4 text-center">
                            <b class="headerCompleteIT">Giao dịch thành công</b>
                            <p class="bodyCompleteIT">Bạn vui lòng kiểm tra lại thông tin giao dịch trong <a href="https://mail.google.com/mail/u/0/#inbox">Email của bạn</a>
                                hoặc trang <a href="#">Lịch sử giao dịch</a> để nắm thông tin.</p>
                            <b class="footerCompleteIT">Mọi thắc mắc xin liên hệ <a href="tel:0376160960">0376160960 (gặp Anh Tấn HandsomeBoy)</a>
                                hoặc <a href="tel:0795768338">0795768338 (gặp Anh Tự SadBoy)</a></b>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
        <jsp:include page="fragment/footer.jsp" />
        <jsp:include page="fragment/go_to_top.jsp" />
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    </body>
</html>
