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
        <title>Login</title>
        <!-- google font -->
        <jsp:include page="../pages/include/login_css.jsp" />
    </head>

    <body>
        <div class="limiter">
            <div class="container-login100 page-background">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="j_spring_security_check" method="post" >
                        <span class="login100-form-logo">
                            <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="">
                        </span>
                        <span class="login100-form-title p-b-34 p-t-27">
                            Đăng Nhập
                        </span>
                        <div class="col-lg-12 p-t-20">
                            <c:if test="${message != null && message != ''}">
                                <div class="alert alert-danger">${message}</div>
                            </c:if>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Enter username">
                            <input class="input100" type="text" name="userName" placeholder="Tên đăng nhập">
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <input class="input100" type="password" name="password" placeholder="Mật khẩu">
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>
                        <!--                        <div class="row">
                                                    <div class="col-lg-6 p-t-20">
                                                        <div class="wrap-input100 validate-input" data-validate="Enter Captcha">
                                                            <input class="input100" type="text" name="captcha" placeholder="captcha">
                        
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 p-t-20">
                                                        <div class="wrap" data-validate="Enter email">
                        
                                                            <span class="focus-input100" data-placeholder="${Capcahr}"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 p-t-20 icon-holder">
                                                        <a href="" class="nav-link nav-toggle">
                                                            <i class="fa fa-refresh"></i>
                                                        </a>
                                                    </div>
                                                </div>-->
                        <div class="contact100-form-checkbox">
                            <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                            <label class="label-checkbox100" for="ckb1">
                                Remember me
                            </label>
                        </div> 
                        <div class="container-login100-form-btn">
                            <button  class="login100-form-btn">
                                Login
                            </button>
                        </div>
                        <div class="text-center p-t-90">
                            <a class="txt1" href="change_password.html">
                                Quyên mật khẩu?/
                            </a>
                            <a class="txt1" href="${pageContext.request.contextPath}/view_register">
                                Tạo tài khoản?
                            </a>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../pages/include/login_js.jsp" />
    </body>

</html>