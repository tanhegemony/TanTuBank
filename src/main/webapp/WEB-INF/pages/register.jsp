<%-- 
    Document   : register
    Created on : Oct 3, 2022, 10:38:03 PM
    Author     : henry
--%>

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
                    <form class="login100-form validate-form">
                        <span class="login100-form-logo">
                           <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                        </span>
                        <span class="login100-form-title p-b-34 p-t-27">
                            Đăng ký
                        </span>
                        <div class="row">
                            <div class="col-lg-6 p-t-20">
                                <div class="wrap-input100 validate-input" data-validate="Enter username">
                                    <input class="input100" type="text" name="username" placeholder="Tên đăng nhập">
                                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                                </div>
                            </div>
                            <div class="col-lg-6 p-t-20">
                                <div class="wrap-input100 validate-input" data-validate="Enter email">
                                    <input class="input100" type="email" name="email" placeholder="Email">
                                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                                </div>
                            </div>
                            <div class="col-lg-6 p-t-20">
                                <div class="wrap-input100 validate-input" data-validate="Enter password">
                                    <input class="input100" type="password" name="pass" placeholder="Mật khẩu">
                                    <span class="focus-input100" data-placeholder="&#xf191;"></span>
                                </div>
                            </div>
                            <div class="col-lg-6 p-t-20">
                                <div class="wrap-input100 validate-input" data-validate="Enter password again">
                                    <input class="input100" type="password" name="pass2" placeholder="Nhập lại mật khẩu">
                                    <span class="focus-input100" data-placeholder="&#xf191;"></span>
                                </div>
                            </div>
                            <div class="col-lg-6 p-t-20">
                                <div class="wrap-input100 validate-input" data-validate="Enter username">
                                    <input class="input100" type="text" name="username" placeholder="captcha">

                                </div>
                            </div>
                            <div class="col-lg-4 p-t-20">
                                <div class="wrap" data-validate="Enter email">

                                    <span class="focus-input100" data-placeholder="634407"></span>
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
                            <a class="txt1" href="#">
                                Bạn đã có tài khoảng?
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../pages/include/login_js.jsp" />
    </body>

</html>
