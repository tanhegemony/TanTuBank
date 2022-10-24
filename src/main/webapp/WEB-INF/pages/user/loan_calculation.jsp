<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tính khoản vay</title>
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
        <link rel="stylesheet" href="<c:url value="/resources/style/css/loan_calculation.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources/style/responsive/loan_calculation_responsive.css" />" />
    </head>
    <body>
        <jsp:include page="fragment/header.jsp" />
        <div class="container-fluid content">
            <div class="row">
                <div class="col">
                    <img src="${pageContext.request.contextPath}/resources/images/banner/banner_personalloan_calculator.jpg" class="img-fluid" alt="">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home"><i class="fa-solid fa-house-chimney"></i> Trang chủ</a></li>
                            <li class="breadcrumb-item active" aria-current="page"><i class="fas fa-calculator"></i> Tính khoản vay</li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div class="container contentForm mt-5">
                <h2 class="text-center">Xem kết quả tính khoản vay</h2>
                <div class="row form">
                    <div class="col-6 inputForm">
                        <h5 class="text-center">Nhập dữ liệu tính toán</h5>
                        <form action="${pageContext.request.contextPath}/resultLoanCalculation" method="post">
                            <div class="form-group">
                                <label for="loanMoney">Số tiền cần vay: </label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Bạn muốn vay bao nhiêu?" 
                                           name="loanMoney" id="loanMoney" value="<c:if test="${errorNumber == true}">${loanMoneyString}</c:if><c:if test="${errorNumber != true}"><fmt:formatNumber type="number" value="${loanMoneyString}" /></c:if>" required>
                                           <div class="input-group-append">
                                               <span class="input-group-text" id="basic-addon2">đồng</span>
                                           </div>
                                    </div>
                                    <script>
                                        var Amount = document.getElementById("loanMoney");
                                        Amount.addEventListener('keyup', function (evt) {
                                            var n = parseFloat(this.value.replace(/\D/g, ''), 10);
                                            Amount.value = n.toLocaleString();
                                        }, false);
                                    </script>
                                        <small class="form-text" style="color: red;">${messageLoanMoney}</small>
                            </div>
                            <div class="form-group">
                                <label for="timeLoan">Thời hạn vay: </label>
                                <div class="input-group mb-3">
                                    <input type="number" min="1" class="form-control" placeholder="Bạn muốn vay bao lâu?" 
                                           name="timeLoan" id="timeLoan" value="${timeLoan}" required>
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon2">tháng</span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="interestRateLoan">Lãi suất vay (0.00): </label>
                                <div class="input-group mb-3">
                                    <input type="number" step="0.01" min="0" class="form-control" placeholder="Bạn vay với lãi suất bao nhiêu?" 
                                           name="interestRateLoan" id="interestRateLoan" 
                                           value="${interestRateLoan}" required>
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon2">%</span>
                                    </div>
                                </div>
                                <small class="form-text" style="color: red;">${messageInterestRateLoan}</small>
                            </div>
                            <div class="text-center buttonCalculation">
                                <button type="submit" class="btn"><i class="fas fa-calculator"></i> Tính toán</button>
                            </div>

                        </form>
                    </div>
                    <div class="col-6 displayForm">
                        <h5 class="text-center">Số tiền phải trả hàng tháng</h5>
                        <div class="form-group">
                            <label>Tổng số tiền gốc và lãi trung bình phải trả hàng tháng: </label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" value="<fmt:formatNumber type="number" value="${averageOriginalPlusInterestPayMonthly}"/>" readonly>
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon2">đồng</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Tiền lãi phải trả hàng tháng: </label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" value="<fmt:formatNumber type="number" value="${interestPayMonthly}" />" readonly>
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon2">đồng</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h2 class="text-center mt-5">Xem chi tiết kết quả tính khoản vay</h2>
                <div class="row">
                    <div class="col">
                        <table class="table table-bordered table-responsive-sm">
                            <tr class="thead">
                                <th class="month">Tháng</th>
                                <th class="originalRemain">Số gốc còn lại</th>
                                <th class="original">Gốc</th>
                                <th class="interest">Lãi</th>
                                <th class="originalPlusInterest">Tổng gốc + Lãi</th>
                            </tr>
                            <tr class="contentTable">
                                <td class="text-center">0</td>
                                <td class="text-right"><c:if test="${errorNumber != true}"><fmt:formatNumber type="number" value="${loanMoneyString}" /></c:if></td>
                                    <td class="text-right">&nbsp;</td>
                                    <td class="text-right">&nbsp;</td>
                                    <td class="text-right">&nbsp;</td>
                                </tr>
                            <c:forEach var="loanCal" items="${loanCalculationList}" >
                                <tr class="contentTable">
                                    <td class="text-center">${loanCal.id}</td>
                                    <td class="text-right"><fmt:formatNumber type="number" value="${loanCal.originalRemain}"/></td>
                                    <td class="text-right"><fmt:formatNumber type="number" value="${loanCal.original}"/></td>
                                    <td class="text-right"><fmt:formatNumber type="number" value="${loanCal.interest}"/></td>
                                    <td class="text-right"><fmt:formatNumber type="number" value="${loanCal.originalPlusInterest}"/></td>
                                </tr>
                            </c:forEach>
                            <tr class="endTable">
                                <th class="text-center">Tổng</th>
                                <td>&nbsp;</td>
                                <td class="text-right"><fmt:formatNumber type="number" value="${totalOriginal}"/></td>
                                <td class="text-right"><fmt:formatNumber type="number" value="${totalInterest}"/></td>
                                <td class="text-right"><fmt:formatNumber type="number" value="${totalOriginalPlusInterest}"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="row note">
                    <div class="col">
                        <p>Lưu ý (<span style="color: red;">*</span>): Công cụ này chỉ mang tính minh hoạ cách tính khoản vay. Không áp dụng thực tế tại các ngân hàng.</p>
                    </div>
                </div>
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
