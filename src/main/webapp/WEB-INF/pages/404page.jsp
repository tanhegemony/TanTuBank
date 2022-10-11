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
                            <form class="form-404" action="${pageContext.request.contextPath}/home" method="get">
					<span class="login100-form-logo" >
                                            <img src="${pageContext.request.contextPath}/resources-management/images/banking.webp" alt="alt"/>
					</span>
					<span class="form404-title p-b-34 p-t-27">
						Error 404
					</span>
					<p class="content-404">Trang bạn đang tìm kiếm không tồn tại hoặc đã xảy ra lỗi khác.</p>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Đi đến trang chủ
						</button>
					</div>
					
				</form>
			</div>
		</div>
        </div>
        <jsp:include page="../pages/include/login_js.jsp" />
    </body>

</html>