<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trang chủ</title>
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
        <link rel="stylesheet" href="<c:url value="/resources/style/css/home_page.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/responsive/home_page_responsive.css" />" />
    </head>
    <body>
        <jsp:include page="fragment/header.jsp" />
        <div class="container-fluid content" >
            <img src="${pageContext.request.contextPath}/resources/images/slide.png" class="img-fluid" alt="" />
            <div class="jumbotron">
                <div class="contentJum">
                    <h1 class="display-4">Xin chào, bạn thân của chúng tôi!</h1>
                    <p class="lead">
                        Website tiện lợi, thân thiện, nhanh chóng với mọi nhà. Với
                        nhiều dịch vụ hấp dẫn mang đến cho khách hàng những trải
                        nghiệm tốt nhất có thể.
                    </p>
                    <hr class="my-4" />
                    <p>
                        Chúng tôi rất hân hạnh được phục vụ quý khách hàng thân
                        thiết đã ghé thăm website chính thức của TanTuBank.
                    </p>
                    <a class="btn btn-lg" href="#" role="button">Xem thêm</a>
                </div>
            </div>
            <div class="row nameService text-center">
                <div class="col">
                    <h5>Các dịch vụ ngân hàng áp dụng</h5>
                </div>
            </div>
            <div class="row contentService justify-content-center">
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img
                                class="card-img-top"
                                src="${pageContext.request.contextPath}/resources/images/icon/mobile-deposit.png"
                                alt=""
                                /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Nạp tiền điện thoại</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img
                                class="card-img-top"
                                src="${pageContext.request.contextPath}/resources/images/icon/electrical-energy.png"
                                alt=""
                                /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Tiền điện</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/icon/water.png" alt=""
                              /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Tiền nước</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/icon/game.png" alt=""
                              /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Nạp game</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/icon/tuition.png" alt=""
                              /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Học phí</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/icon/internet.png" alt=""
                              /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Internet</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/icon/foody.png" alt=""
                              /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Foody</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img
                                class="card-img-top"
                                src="${pageContext.request.contextPath}/resources/images/icon/movies-ticket.png"
                                alt=""
                                /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Mua vé xem phim</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img
                                class="card-img-top"
                                src="${pageContext.request.contextPath}/resources/images/icon/fly-ticket.png"
                                alt=""
                                /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Mua vé máy bay</p></a>
                        </div>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-1 mt-4 mb-1 text-center">
                        <a href="#"
                           ><img
                                class="card-img-top"
                                src="${pageContext.request.contextPath}/resources/images/icon/train-ticket.png"
                                alt=""
                                /></a>
                        <div class="card-body">
                            <a href="#"><p class="card-text">Mua vé tàu hoả</p></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="fragment/footer.jsp" />
        <jsp:include page="fragment/go_to_top.jsp" />
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
