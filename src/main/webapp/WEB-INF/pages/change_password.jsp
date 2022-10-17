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
        <title>change password</title>
        <!-- google font -->
        <jsp:include page="../pages/include/login_css.jsp" />
    </head>

    <body>
        <div class="limiter">
            <div class="container-login100 page-background">
                <div class="wrap-login100">
                    <c:if test="${checkEmailAndPhone == false}">
                        <form class="login100-form validate-form" action="${pageContext.request.contextPath}/checkEmailAndPhone" method="post" >
                            <span class="login100-form-logo">
                                <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                            </span>
                            <span class="login100-form-title p-b-34 p-t-27">
                                Dổi mật khẩu
                            </span>
                            <div class="col-lg-12 p-t-20">
                                <c:if test="${message != null && message != ''}">
                                    <div class="alert alert-danger">${message}</div>
                                </c:if>
                            </div>
                            <div class="wrap-input100 validate-input" data-validate="Enter UserName">
                                <input class="input100" type="text" name="userName" placeholder="Tên đăng nhập">
                                <span class="focus-input100" data-placeholder="&#xf207;"></span>
                            </div>
                            <div class="wrap-input100 validate-input" data-validate="Enter email and phone">
                                <input class="input100" type="text" name="emailAndPhone" placeholder="Nhập email hoặt phone">
                                <span class="focus-input100" data-placeholder="&#xf207;"></span>
                            </div>
                            <div class="container-login100-form-btn">
                                    <button class="login100-form-btn">Tiếp tục</button>
                            </div>                          
                            <div class="container-login100-form-btn mt-3">
                                <a href="${pageContext.request.contextPath}/login" class="login100-form-btn">Quay lại</a>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${checkEmailAndPhone == true}">    
                        <form class="login100-form validate-form" action="${pageContext.request.contextPath}/chekEmail" method="post" >
                            <span class="login100-form-logo">
                                <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                            </span>
                            <span class="login100-form-title p-b-34 p-t-27">
                                Xác thực
                            </span>
                            <c:if test="${message != null && message != ''}">
                                <div class="alert alert-danger" style="text-align: center">${message}</div>
                            </c:if>
                            <div></div>
                            <span style="color: white">Mã xác thực đã được gửi đến email: <spam class="alert-link">${Email}</spam></span>
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

                    <c:if test="${checkPassword == true}">
                        <form class="login100-form validate-form" action="${pageContext.request.contextPath}/changePassword" method="post" >
                            <span class="login100-form-logo">
                                <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                            </span>
                            <span class="login100-form-title p-b-34 p-t-27">
                                Dổi mật khẩu
                            </span>
                            <div class="col-lg-12 p-t-20">
                                <c:if test="${message != null && message != ''}">
                                    <div class="alert alert-danger">${message}</div>
                                </c:if>
                            </div>
                            <div class="wrap-input100 validate-input" data-validate="Enter username">
                                <input class="input100" type="password" name="password" placeholder="Mật khẩu">
                                <span class="focus-input100" data-placeholder="&#xf207;"></span>
                            </div>
                            <div class="wrap-input100 validate-input" data-validate="Enter password">
                                <input class="input100" type="password" name="resetPassword" placeholder="Nhập mật khẩu">
                                <span class="focus-input100" data-placeholder="&#xf191;"></span>
                            </div>
                             <div class="container-login100-form-btn mt-3">
                                <button class="login100-form-btn" type="submit">
                                    Lưu mật khẩu
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