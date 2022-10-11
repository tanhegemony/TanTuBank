<%-- 
    Document   : register
    Created on : Oct 3, 2022, 10:38:03 PM
    Author     : henry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta name="description" content="Responsive Admin Template" />
        <meta name="author" content="SmartUniversity" />
        <title>Register</title>
        <jsp:include page="../pages/include/login_css.jsp" />
    </head>

    <body>
        <div class="limiter">
            <div class="container-login100 page-background">
                <div class="wrap-login100">
                    <c:if test="${displayCheckAccountBySendMail == false}">
                        <form class="login100-form validate-form" action="${pageContext.request.contextPath}/register" method="post">
                            <span class="login100-form-logo">
                                <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                            </span>
                            <span class="login100-form-title p-b-34 p-t-27">
                                Đăng ký
                            </span>
                             <c:if test="${message != null && message != ''}">
                                        <div class="alert alert-danger">${message}</div>
                               </c:if>
                            <div class="row">
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter Customer Name">
                                        <input class="input100" type="text" name="customerName" placeholder="Tên người dùng">
                                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                                    </div>
                                </div>
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter Phone">
                                        <input class="input100" type="number" name="customerPhone" placeholder="Số điện thoại">
                                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                                    </div>
                                </div>
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter username">
                                        <input class="input100" type="text" name="userName" placeholder="Tên đăng nhập">
                                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                                    </div>
                                </div>
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter email">
                                        <input class="input100" type="email" name="customerEmail" placeholder="Email">
                                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                                    </div>
                                </div>
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter password">
                                        <input class="input100" type="password" name="password" placeholder="Mật khẩu">
                                        <span class="focus-input100" data-placeholder="&#xf191;"></span>
                                    </div>
                                </div>
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter password again">
                                        <input class="input100" type="password" name="passwordAgain" placeholder="Nhập lại mật khẩu">
                                        <span class="focus-input100" data-placeholder="&#xf191;"></span>
                                    </div>
                                </div>
                                <div class="col-lg-6 p-t-20">
                                    <div class="wrap-input100 validate-input" data-validate="Enter captcha">
                                        <input class="input100" type="text" name="username" placeholder="captcha">

                                    </div>
                                </div>
                                <div class="col-lg-4 p-t-20">
                                    <div class="wrap" data-validate="Enter captcha">

                                        <span class="focus-input100" data-placeholder="${captcha}"></span>
                                    </div>
                                </div>
                                <div class="col-lg-2 p-t-20 icon-holder">
                                    <a href="" class="nav-link nav-toggle">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="container-login100-form-btn">
                                <button class="login100-form-btn">
                                    Tiếp tục
                                </button>
                            </div>
                            <div class="text-center p-t-90">
                                <a class="txt1" href="${pageContext.request.contextPath}/login">
                                    Bạn đã có tài khoản?
                                </a>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${displayCheckAccountBySendMail == true}">
                    <form class="login100-form validate-form" action="${pageContext.request.contextPath}/checkAccountBySendEmail" method="post" >
                        <span class="login100-form-logo">
                            <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                        </span>
                        <span class="login100-form-title p-b-34 p-t-27">
                            Xác thực
                        </span>
                         <c:if test="${message != null && message != ''}">
                                        <div class="alert alert-danger">${message}</div>
                               </c:if>
                        <div></div>
                        <span style="color: white">Mã xác thực đã được gửi đến email :<spam class="alert-link">${Email}</spam></span>
                        <script>
                            function startTimer(duration, display) {
                                var timer = duration,
                                         seconds;
                                setInterval(function () {
                                    seconds = parseInt(timer % 60, 10);

                                    seconds = seconds < 10 ? "0" + seconds : seconds;

                                    display.textContent = seconds;

                                    if (--timer < 0) {
                                        timer = duration;
                                    }
                                    if (timer == 0) {
                                        window.location.href = 'view_register';
                                    }
                                }, 1000);
                            }

                            window.onload = function () {
                                var fifteenMinutes = 60 * 1,
                                        display = document.querySelector('#time');
                                startTimer(fifteenMinutes, display);
                            };
                        </script>
                        <div class="form-group mt-3">
                            <input type="test" style="color: black;border: 1px solid gray;"  class="form-control form-control-lg"  name="codeRandomCheck" maxlength="6"placeholder="Codes" required>
                        </div>
                        <div class="container-login100-form-btn">
                            <span class="" style="color: white">Thời gian nhập mã còn:     <span class="" style="color: red" id="time"></span></span>
                        </div>
                        <div class="container-login100-form-btn mt-3">
                            <button class="login100-form-btn" type="submit">
                                xác nhận 
                            </button>
                        </div>
                    </form>
                    </c:if>     
                </div>
            </div>
        </div>
        <jsp:include page="../pages/include/login_js.jsp" />
    </body>

</html>
