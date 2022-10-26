<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <!-- Make By Hồ Ngọc Tấn  -->
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta name="description" content="Responsive Admin Template" />
        <meta name="author" content="SmartUniversity" />
        <title>Quản lý tài khoản ngân hàng -- <c:if test="${nav == 'managePaymentBankAccount'}">Tài khoản thanh toán</c:if>
            <c:if test="${nav == 'manageSavingBankAccount'}">Tài khoản tiết kiệm</c:if>
            <c:if test="${nav == 'manageExternalBankAccount'}">Tài khoản liên ngân hàng</c:if>
            <c:if test="${nav == 'manageUnActiveBankAccount'}">Tài khoản ngân hàng chưa hoạt động</c:if>
            </title>
            <!-- google font -->
            <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet" type="text/css" />
            <link rel="stylesheet" href="<c:url value="/resources/style/fontawesome-free-6.2.0-web/css/all.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources-management/assets/css/manage_account_banking.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources-management/assets/responsive/manage_account_banking_responsive.css" />" />
        <jsp:include page="../include/css.jsp" />
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-white dark-sidebar-color logo-dark">
        <div class="page-wrapper">
            <!-- start header -->
            <jsp:include page="../include/header.jsp" />
            <!-- end header -->
            <!-- start page container -->
            <div class="page-container">
                <!-- start sidebar menu -->
                <jsp:include page="../include/menu.jsp" />
                <!-- end sidebar menu -->
                <!-- start page content -->
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="page-bar">
                            <div class="page-title-breadcrumb">
                                <div class=" pull-left">
                                    <div class="page-title">
                                        <c:if test="${nav == 'managePaymentBankAccount'}">Quản lý tài khoản ngân hàng thanh toán</c:if>
                                        <c:if test="${nav == 'manageSavingBankAccount'}">Quản lý tài khoản ngân hàng tiết kiệm</c:if>
                                        <c:if test="${nav == 'manageExternalBankAccount'}">Quản lý tài khoản liên ngân hàng</c:if>
                                        <c:if test="${nav == 'manageUnActiveBankAccount'}">Quản lý tài khoản ngân hàng chưa hoạt động</c:if>
                                        </div>
                                    </div>
                                    <ol class="breadcrumb page-breadcrumb pull-right">
                                        <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                        </li>
                                        <li><a class="parent-item" href="index.html">Quản lý tài khoản ngân hàng</a>&nbsp;<i class="fa fa-angle-right"></i>
                                        </li>
                                        <li class="active">
                                        <c:if test="${nav == 'managePaymentBankAccount'}">Tài khoản thanh toán</c:if>
                                        <c:if test="${nav == 'manageSavingBankAccount'}">Tài khoản tiết kiệm</c:if>
                                        <c:if test="${nav == 'manageExternalBankAccount'}">Tài khoản liên ngân hàng</c:if>
                                        <c:if test="${nav == 'manageUnActiveBankAccount'}">Tài khoản ngân hàng chưa hoạt động</c:if>
                                        </li>
                                    </ol>
                                </div>
                            </div>
                            <!-- chart start -->
                            <div class="row manageAccountBank">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-4 openAccountButton">
                                        <c:if test="${nav == 'managePaymentBankAccount'}">
                                            <button onclick="location.href='${pageContext.request.contextPath}/management/viewOpenOrEditBankAccount'" class="btn">Mở tài khoản thanh toán</button>
                                        </c:if>
                                        <c:if test="${nav == 'manageSavingBankAccount'}">
                                            <button onclick="location.href='${pageContext.request.contextPath}/management/viewOpenOrEditBankAccount'" class="btn">Mở tài khoản tiết kiệm</button>
                                        </c:if>
                                    </div>
                                    <div class="col-4 pageSize">
                                        <form>
                                            <input type="hidden" name="action" value="${action}">
                                            <input type="hidden" name="nav" value="${nav}">
                                            <c:if test="${action == 'filterBankAccount'}">
                                                <input type="hidden" name="startDate" value="${startDate}">
                                                <input type="hidden" name="endDate" value="${endDate}">
                                                <input type="hidden" name="accountStatus" value="${accountStatus}">
                                                <input type="hidden" name="sortBy" value="${sortBy}">
                                            </c:if>
                                            <input type="hidden" name="searchValue" value="${searchValue}">
                                            <input type="hidden" name="page" value="1">
                                            <div class="form-inline">
                                                <label for="size">Hiển thị: </label>
                                                <select class="form-control ml-2 mr-2" name="size" id="size" onchange="this.form.submit();">
                                                    <option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
                                                    <option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
                                                    <option value="15" <c:if test="${pageSize == 15}">selected</c:if>>15</option>
                                                    </select>
                                                <c:if test="${nav == 'managePaymentBankAccount'}">
                                                    tài khoản thanh toán
                                                </c:if>
                                                <c:if test="${nav == 'manageSavingBankAccount'}">
                                                    tài khoản tiết kiệm
                                                </c:if>
                                                <c:if test="${nav == 'manageExternalBankAccount'}">
                                                    tài khoản liên ngân hàng
                                                </c:if>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-4 searchAccountBank">
                                        <mvc:form action="${pageContext.request.contextPath}/management/searchBankAccount" method="get">
                                            <input type="hidden" name="nav" value="${nav}">
                                            <input type="hidden" name="accountStatus" value="${accountStatus}">
                                            <input type="hidden" name="sortBy" value="${sortBy}">
                                            <div class="form-check-inline">
                                                <input type="text"
                                                       class="form-control" name="searchValue" id="searchValue" 
                                                       placeholder="Tìm kiếm tài khoản" value="${searchValue}">
                                                <button class="btn btn-dark"><i class="fas fa-search"></i></button>
                                            </div>
                                        </mvc:form>
                                    </div>
                                </div>
                                <div class="row nameFilter justify-content-center"> 
                                    <div class="col-3" >
                                        <h4>Bộ lọc tài khoản</h4> 
                                    </div>
                                </div>
                                <div class="row makeFilter">
                                    <div class="col">
                                        <mvc:form action="${pageContext.request.contextPath}/management/resultFilter" method="GET">
                                            <input type="hidden" name="action" value="${action}">
                                            <input type="hidden" name="nav" value="${nav}">
                                            <input type="hidden" name="searchValue" value="${searchValue}">
                                            <input type="hidden" name="size" value="${pageSize}">
                                            <div class="row contentFilter">
                                                <div class="col-3">
                                                    <div class="form-group">
                                                        <label for="startDate">Ngày bắt đầu: </label>
                                                        <input type="date"  max="<c:if test="${endDate != ''}">${endDate}</c:if><c:if test="${endDate == '' || action == 'viewBankAccounts' || action == 'searchBankAccount'}">${today}</c:if>" class="form-control" name="startDate" 
                                                               id="startDate" value="${startDate}" onchange="this.form.submit();">
                                                    </div>
                                                </div>
                                                <div class="col-3">
                                                    <div class="form-group">
                                                        <label for="endDate">Ngày kết thúc: </label>
                                                        <input type="date" min="${startDate}" max="${today}" class="form-control"  name="endDate" 
                                                               id="endDate" value="${endDate}" onchange="this.form.submit();">
                                                    </div>
                                                </div>
                                                <div class="col-3">
                                                    <div class="form-group">
                                                        <label for="accountStatus">Trạng thái: </label>
                                                        <select class="form-control" name="accountStatus" id="accountStatus" onchange="this.form.submit();">
                                                            <option <c:if test="${accountStatus == ''}">selected</c:if> value="">Tất cả</option>
                                                            <c:forEach var="aStatus" items="${accountStatusList}">
                                                                <c:if test="${accountStatus == aStatus}">
                                                                    <option value="${aStatus}" selected>${aStatus}</option>
                                                                </c:if>
                                                                <c:if test="${accountStatus != aStatus}">
                                                                    <option value="${aStatus}">${aStatus}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-3">
                                                    <div class="form-group">
                                                        <label for="sortBy">Sắp xếp theo: </label>
                                                        <select class="form-control" name="sortBy" id="sortBy" onchange="this.form.submit();">
                                                            <option value="idAscending" <c:if test="${sortBy == 'idAscending'}">selected</c:if> >Id tài khoản tăng dần</option>
                                                            <option value="idDescending" <c:if test="${sortBy == 'idDescending'}">selected</c:if>>Id tài khoản giảm dần</option>
                                                            <option value="accountNumberAscending"<c:if test="${sortBy == 'accountNumberAscending'}">selected</c:if>>Số tài khoản tăng dần</option>
                                                            <option value="accountNumberDescending" <c:if test="${sortBy == 'accountNumberDescending'}">selected</c:if>>Số tài khoản giảm dần</option>
                                                            <option value="accountNameAscending" <c:if test="${sortBy == 'accountNameAscending'}">selected</c:if>>Tên tài khoản tăng dần</option>
                                                            <option value="accountNameDescending" <c:if test="${sortBy == 'accountNameDescending'}">selected</c:if>>Tên tài khoản giảm dần</option>
                                                            <option value="createDateAscending" <c:if test="${sortBy == 'createDateAscending'}">selected</c:if>>Ngày tạo tăng dần</option>
                                                            <option value="createDateDescending" <c:if test="${sortBy == 'createDateDescending'}">selected</c:if>>Ngày tạo giảm dần</option>
                                                            <option value="customerIdAscending" <c:if test="${sortBy == 'customerIdAscending'}">selected</c:if>>Id khách hàng tăng dần</option>
                                                            <option value="customerIdDescending" <c:if test="${sortBy == 'customerIdDescending'}">selected</c:if>>Id khách hàng giảm dần dần</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>

                                        </mvc:form>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col">
                                        <table class="table table-striped table-responsive text-center">
                                            <tr class="headerManageAccountBank thead-dark">
                                                <th class="accountNumber">Số tài khoản</th>
                                                <th class="nameAccount">Tên tài khoản</th>
                                                <th class="typeAccount">Loại tài khoản</th>
                                                <th class="nameBank">Tên ngân hàng</th>
                                                <th class="createDate">Ngày tạo</th>
                                                <th class="staff">Nhân viên</th>
                                                <th class="customer">Khách hàng</th>
                                                <th class="status">Trạng thái</th>
                                                <th class="manage">Quản lý</th>
                                            </tr>
                                            <c:if test="${bankAccounts.content.size() <= 0 || bankAccounts.content == null}">
                                                <tr>
                                                    <td colspan="9"><h4 style="font-weight: bolder;color:red; ">Không tồn tại tài khoản ngân hàng nào</h4></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${bankAccounts.content.size() > 0}">
                                                <c:forEach var="ba" items="${bankAccounts.content}">
                                                    <tr>
                                                        <td>${ba.accountNumber}</td>
                                                        <td>${ba.accountName}</td>
                                                        <td>${ba.accountType}</td>
                                                        <td>${ba.bank.bankName}</td>
                                                        <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${ba.createDate}" /></td>
                                                        <c:if test="${ba.staff == null}">
                                                            <td>Customer thực hiện</td>
                                                        </c:if>
                                                        <c:if test="${ba.staff != null}">
                                                            <td>ID: ${ba.staff.id}-Name: ${ba.staff.staffName} </td>
                                                        </c:if>
                                                        <td>
                                                            <c:if test="${ba.customer == null}">
                                                                Staff thực hiện
                                                            </c:if>
                                                            <c:if test="${ba.customer != null}">
                                                                <a href="#">${ba.customer.id}</a>
                                                            </c:if>
                                                        </td>
                                                        <td class="<c:if test="${ba.bankAccountStatus == 'ACTIVE'}">statusActive</c:if>
                                                            <c:if test="${ba.bankAccountStatus == 'UNACTIVE'}">statusUnactive</c:if>
                                                            <c:if test="${ba.bankAccountStatus == 'LOCK'}">statusLock</c:if>">
                                                            <i class="fas fa-circle"></i> ${ba.bankAccountStatus}</td>
                                                        <td class="buttonManage">
                                                            <c:if test="${ba.bankAccountStatus == 'ACTIVE'}">
                                                                <a onclick="return confirm('Bạn có chắc chắn muốn KHOÁ tài khoản ngân hàng --${ba.accountNumber}-- không?')" href="${pageContext.request.contextPath}/management/changeBankAccountStatus/${action}/${nav}/${currentPage}/${pageSize}/${ba.id}/LOCK" class="btn btn-danger"><i class="fas fa-lock"></i> Lock</a>
                                                                <a onclick="location.href='${pageContext.request.contextPath}/management/editBankAccount/${ba.id}'" class="btn btn-secondary" style="color: white;"><i class="fas fa-file-edit"></i> Edit</a>
                                                            </c:if>
                                                            <c:if test="${ba.bankAccountStatus == 'UNACTIVE'}">
                                                                <a onclick="return confirm('Bạn có chắc chắn muốn MỞ HOẠT ĐỘNG tài khoản ngân hàng --${ba.accountNumber}-- không?')" href="${pageContext.request.contextPath}/management/changeBankAccountStatus/${action}/${nav}/${currentPage}/${pageSize}/${ba.id}/ACTIVE" class="btn activeButton"><i class="fas fa-circle"></i> Active</a>
                                                                <a onclick="return confirm('Bạn có chắc chắn muốn XOÁ tài khoản ngân hàng --${ba.accountNumber}-- không?')" href="${pageContext.request.contextPath}/management/deleteBankAccount/${action}/${nav}/${ba.id}" class="btn btn-dark" style="color: white;"><i class="fas fa-trash-alt"></i> Delete</a>
                                                            </c:if>
                                                            <c:if test="${ba.bankAccountStatus == 'LOCK'}">
                                                                <a onclick="return confirm('Bạn có chắc chắn muốn MỞ KHOÁ tài khoản ngân hàng --${ba.accountNumber}-- không?')" href="${pageContext.request.contextPath}/management/changeBankAccountStatus/${action}/${nav}/${currentPage}/${pageSize}/${ba.id}/ACTIVE" class="btn btn-primary"><i class="fas fa-lock-open"></i> Unlock</a>
                                                                <a onclick="return confirm('Bạn có chắc chắn muốn XOÁ tài khoản ngân hàng --${ba.accountNumber}-- không?')" href="${pageContext.request.contextPath}/management/deleteBankAccount/${action}/${nav}/${ba.id}" class="btn btn-dark" style="color: white;"><i class="fas fa-trash-alt"></i> Delete</a>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>

                                        </table>
                                    </div>
                                </div>
                                <c:if test="${bankAccounts.content.size() > 0}">
                                    <div class="row paginate">
                                        <div class="col">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination">
                                                    <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                                                            <a class="page-link" 
                                                            <c:if test="${action == 'viewBankAccounts'}">
                                                                href="${pageContext.request.contextPath}/management/viewManageBankAccount?nav=${nav}&page=1&size=${pageSize}"
                                                            </c:if>
                                                            <c:if test="${action == 'searchBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/searchBankAccount?nav=${nav}&searchValue=${searchValue}&page=1&size=${pageSize}"
                                                            </c:if> 
                                                            <c:if test="${action == 'filterBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/resultFilter?action=filterBankAccount&nav=${nav}&searchValue=${searchValue}&startDate=${startDate}&endDate=${endDate}&accountStatus=${accountStatus}&sortBy=${sortBy}&page=1&size=${pageSize}"
                                                            </c:if>    
                                                            aria-label="First">

                                                            <span aria-hidden="true">Trang đầu</span>
                                                        </a>
                                                    </li>
                                                    <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                                                            <a class="page-link" 
                                                            <c:if test="${action == 'viewBankAccounts'}">
                                                                href="${pageContext.request.contextPath}/management/viewManageBankAccount?nav=${nav}&page=${currentPage-1}&size=${pageSize}"
                                                            </c:if>
                                                            <c:if test="${action == 'searchBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/searchBankAccount?nav=${nav}&searchValue=${searchValue}&page=${currentPage-1}&size=${pageSize}"
                                                            </c:if> 
                                                            <c:if test="${action == 'filterBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/resultFilter?action=filterBankAccount&nav=${nav}&searchValue=${searchValue}&startDate=${startDate}&endDate=${endDate}&accountStatus=${accountStatus}&sortBy=${sortBy}&page=${currentPage-1}&size=${pageSize}"
                                                            </c:if>    
                                                            aria-label="Previous">
                                                            <span aria-hidden="true">&laquo;</span>
                                                        </a>
                                                    </li>
                                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                                        <c:if test="${currentPage == i}">
                                                            <li class="page-item active" >
                                                                <a class="page-link " style="background-color: black;border: 1px solid black;" 
                                                                   <c:if test="${action == 'viewBankAccounts'}">
                                                                       href="${pageContext.request.contextPath}/management/viewManageBankAccount?nav=${nav}&page=${i}&size=${pageSize}"
                                                                   </c:if>
                                                                   <c:if test="${action == 'searchBankAccount'}">
                                                                       href="${pageContext.request.contextPath}/management/searchBankAccount?nav=${nav}&searchValue=${searchValue}&page=${i}&size=${pageSize}"
                                                                   </c:if>
                                                                   <c:if test="${action == 'filterBankAccount'}">
                                                                       href="${pageContext.request.contextPath}/management/resultFilter?action=filterBankAccount&nav=${nav}&searchValue=${searchValue}&startDate=${startDate}&endDate=${endDate}&accountStatus=${accountStatus}&sortBy=${sortBy}&page=${i}&size=${pageSize}"
                                                                   </c:if>
                                                                   >${i}
                                                                </a>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${currentPage != i}">
                                                            <li class="page-item">
                                                                <a class="page-link" 
                                                                   <c:if test="${action == 'viewBankAccounts'}">
                                                                       href="${pageContext.request.contextPath}/management/viewManageBankAccount?nav=${nav}&page=${i}&size=${pageSize}"
                                                                   </c:if>
                                                                   <c:if test="${action == 'searchBankAccount'}">
                                                                       href="${pageContext.request.contextPath}/management/searchBankAccount?nav=${nav}&searchValue=${searchValue}&page=${i}&size=${pageSize}"
                                                                   </c:if>  
                                                                   <c:if test="${action == 'filterBankAccount'}">
                                                                       href="${pageContext.request.contextPath}/management/resultFilter?action=filterBankAccount&nav=${nav}&searchValue=${searchValue}&startDate=${startDate}&endDate=${endDate}&accountStatus=${accountStatus}&sortBy=${sortBy}&page=${i}&size=${pageSize}"
                                                                   </c:if>
                                                                   >${i}
                                                                </a>
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                    <li class="page-item <c:if test="${currentPage == totalPage}">disabled</c:if>">
                                                            <a class="page-link" 
                                                            <c:if test="${action == 'viewBankAccounts'}">
                                                                href="${pageContext.request.contextPath}/management/viewManageBankAccount?nav=${nav}&page=${currentPage+1}&size=${pageSize}"
                                                            </c:if>
                                                            <c:if test="${action == 'searchBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/searchBankAccount?nav=${nav}&searchValue=${searchValue}&page=${currentPage+1}&size=${pageSize}"
                                                            </c:if>
                                                            <c:if test="${action == 'filterBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/resultFilter?action=filterBankAccount&nav=${nav}&searchValue=${searchValue}&startDate=${startDate}&endDate=${endDate}&accountStatus=${accountStatus}&sortBy=${sortBy}&page=${currentPage+1}&size=${pageSize}"
                                                            </c:if>    
                                                            aria-label="Next">
                                                            <span aria-hidden="true">&raquo;</span>
                                                        </a>
                                                    </li>
                                                    <li class="page-item <c:if test="${currentPage == totalPage}">disabled</c:if>">
                                                            <a class="page-link" 
                                                            <c:if test="${action == 'viewBankAccounts'}">
                                                                href="${pageContext.request.contextPath}/management/viewManageBankAccount?nav=${nav}&page=${totalPage}&size=${pageSize}"
                                                            </c:if>
                                                            <c:if test="${action == 'searchBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/searchBankAccount?nav=${nav}&searchValue=${searchValue}&page=${totalPage}&size=${pageSize}"
                                                            </c:if> 
                                                            <c:if test="${action == 'filterBankAccount'}">
                                                                href="${pageContext.request.contextPath}/management/resultFilter?action=filterBankAccount&nav=${nav}&searchValue=${searchValue}&startDate=${startDate}&endDate=${endDate}&accountStatus=${accountStatus}&sortBy=${sortBy}&page=${totalPage}&size=${pageSize}"
                                                            </c:if>    
                                                            aria-label="Finally">
                                                            <span aria-hidden="true">Trang cuối</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- end page content -->
                <!-- start chat sidebar -->
                <div class="chat-sidebar-container" data-close-on-body-click="false">
                    <div class="chat-sidebar">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a href="#quick_sidebar_tab_1" class="nav-link active tab-icon" data-toggle="tab">Theme
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#quick_sidebar_tab_2" class="nav-link tab-icon" data-toggle="tab"> Chat
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#quick_sidebar_tab_3" class="nav-link tab-icon" data-toggle="tab">  Settings
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane chat-sidebar-settings in show active animated shake" role="tabpanel" id="quick_sidebar_tab_1">
                                <div class="slimscroll-style">
                                    <div class="theme-light-dark">
                                        <h6>Sidebar Theme</h6>
                                        <button type="button" data-theme="white" class="btn lightColor btn-outline btn-circle m-b-10 theme-button">Light Sidebar</button>
                                        <button type="button" data-theme="dark" class="btn dark btn-outline btn-circle m-b-10 theme-button">Dark Sidebar</button>
                                    </div>
                                    <div class="theme-light-dark">
                                        <h6>Sidebar Color</h6>
                                        <ul class="list-unstyled">
                                            <li class="complete">
                                                <div class="theme-color sidebar-theme">
                                                    <a href="#" data-theme="white"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="dark"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="blue"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="indigo"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="cyan"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="green"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="red"><span class="head"></span><span class="cont"></span></a>
                                                </div>
                                            </li>
                                        </ul>
                                        <h6>Header Brand color</h6>
                                        <ul class="list-unstyled">
                                            <li class="theme-option">
                                                <div class="theme-color logo-theme">
                                                    <a href="#" data-theme="logo-white"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="logo-dark"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="logo-blue"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="logo-indigo"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="logo-cyan"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="logo-green"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="logo-red"><span class="head"></span><span class="cont"></span></a>
                                                </div>
                                            </li>
                                        </ul>
                                        <h6>Header color</h6>
                                        <ul class="list-unstyled">
                                            <li class="theme-option">
                                                <div class="theme-color header-theme">
                                                    <a href="#" data-theme="header-white"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="header-dark"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="header-blue"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="header-indigo"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="header-cyan"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="header-green"><span class="head"></span><span class="cont"></span></a>
                                                    <a href="#" data-theme="header-red"><span class="head"></span><span class="cont"></span></a>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- Start Doctor Chat -->
                            <div class="tab-pane chat-sidebar-chat animated slideInRight" id="quick_sidebar_tab_2">
                                <div class="chat-sidebar-list">
                                    <div class="chat-sidebar-chat-users slimscroll-style" data-rail-color="#ddd" data-wrapper-class="chat-sidebar-list">
                                        <div class="chat-header">
                                            <h5 class="list-heading">Online</h5>
                                        </div>
                                        <ul class="media-list list-items">
                                            <li class="media"><img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user3.jpg" width="35" height="35" alt="...">
                                                <i class="online dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">John Deo</h5>
                                                    <div class="media-heading-sub">Spine Surgeon</div>
                                                </div>
                                            </li>
                                            <li class="media">
                                                <div class="media-status">
                                                    <span class="badge badge-success">5</span>
                                                </div> <img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user1.jpg" width="35" height="35" alt="...">
                                                <i class="busy dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Rajesh</h5>
                                                    <div class="media-heading-sub">Director</div>
                                                </div>
                                            </li>
                                            <li class="media"><img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user5.jpg" width="35" height="35" alt="...">
                                                <i class="away dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Jacob Ryan</h5>
                                                    <div class="media-heading-sub">Ortho Surgeon</div>
                                                </div>
                                            </li>
                                            <li class="media">
                                                <div class="media-status">
                                                    <span class="badge badge-danger">8</span>
                                                </div> <img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user4.jpg" width="35" height="35" alt="...">
                                                <i class="online dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Kehn Anderson</h5>
                                                    <div class="media-heading-sub">CEO</div>
                                                </div>
                                            </li>
                                            <li class="media"><img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user2.jpg" width="35" height="35" alt="...">
                                                <i class="busy dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Sarah Smith</h5>
                                                    <div class="media-heading-sub">Anaesthetics</div>
                                                </div>
                                            </li>
                                            <li class="media"><img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user7.jpg" width="35" height="35" alt="...">
                                                <i class="online dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Vlad Cardella</h5>
                                                    <div class="media-heading-sub">Cardiologist</div>
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="chat-header">
                                            <h5 class="list-heading">Offline</h5>
                                        </div>
                                        <ul class="media-list list-items">
                                            <li class="media">
                                                <div class="media-status">
                                                    <span class="badge badge-warning">4</span>
                                                </div> <img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user6.jpg" width="35" height="35" alt="...">
                                                <i class="offline dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Jennifer Maklen</h5>
                                                    <div class="media-heading-sub">Nurse</div>
                                                    <div class="media-heading-small">Last seen 01:20 AM</div>
                                                </div>
                                            </li>
                                            <li class="media"><img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user8.jpg" width="35" height="35" alt="...">
                                                <i class="offline dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Lina Smith</h5>
                                                    <div class="media-heading-sub">Ortho Surgeon</div>
                                                    <div class="media-heading-small">Last seen 11:14 PM</div>
                                                </div>
                                            </li>
                                            <li class="media">
                                                <div class="media-status">
                                                    <span class="badge badge-success">9</span>
                                                </div> <img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user9.jpg" width="35" height="35" alt="...">
                                                <i class="offline dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Jeff Adam</h5>
                                                    <div class="media-heading-sub">Compounder</div>
                                                    <div class="media-heading-small">Last seen 3:31 PM</div>
                                                </div>
                                            </li>
                                            <li class="media"><img class="media-object" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user10.jpg" width="35" height="35" alt="...">
                                                <i class="offline dot"></i>
                                                <div class="media-body">
                                                    <h5 class="media-heading">Anjelina Cardella</h5>
                                                    <div class="media-heading-sub">Physiotherapist</div>
                                                    <div class="media-heading-small">Last seen 7:45 PM</div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="chat-sidebar-item">
                                    <div class="chat-sidebar-chat-user">
                                        <div class="page-quick-sidemenu">
                                            <a href="javascript:;" class="chat-sidebar-back-to-list">
                                                <i class="fa fa-angle-double-left"></i>Back
                                            </a>
                                        </div>
                                        <div class="chat-sidebar-chat-user-messages">
                                            <div class="post out">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/dp.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Kiran Patel</a> <span class="datetime">9:10</span>
                                                    <span class="body-out"> could you send me menu icons ? </span>
                                                </div>
                                            </div>
                                            <div class="post in">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user5.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Jacob Ryan</a> <span class="datetime">9:10</span>
                                                    <span class="body"> please give me 10 minutes. </span>
                                                </div>
                                            </div>
                                            <div class="post out">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/dp.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Kiran Patel</a> <span class="datetime">9:11</span>
                                                    <span class="body-out"> ok fine :) </span>
                                                </div>
                                            </div>
                                            <div class="post in">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user5.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Jacob Ryan</a> <span class="datetime">9:22</span>
                                                    <span class="body">Sorry for
                                                        the delay. i sent mail to you. let me know if it is ok or not.</span>
                                                </div>
                                            </div>
                                            <div class="post out">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/dp.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Kiran Patel</a> <span class="datetime">9:26</span>
                                                    <span class="body-out"> it is perfect! :) </span>
                                                </div>
                                            </div>
                                            <div class="post out">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/dp.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Kiran Patel</a> <span class="datetime">9:26</span>
                                                    <span class="body-out"> Great! Thanks. </span>
                                                </div>
                                            </div>
                                            <div class="post in">
                                                <img class="avatar" alt="" src="${pageContext.request.contextPath}/resources-management/assets/img/user/user5.jpg" />
                                                <div class="message">
                                                    <span class="arrow"></span> <a href="javascript:;" class="name">Jacob Ryan</a> <span class="datetime">9:27</span>
                                                    <span class="body"> it is my pleasure :) </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="chat-sidebar-chat-user-form">
                                            <div class="input-group">
                                                <input type="text" class="form-control" placeholder="Type a message here...">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn deepPink-bgcolor">
                                                        <i class="fa fa-arrow-right"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Doctor Chat -->
                            <!-- Start Setting Panel -->
                            <div class="tab-pane chat-sidebar-settings animated slideInUp" id="quick_sidebar_tab_3">
                                <div class="chat-sidebar-settings-list slimscroll-style">
                                    <div class="chat-header">
                                        <h5 class="list-heading">Layout Settings</h5>
                                    </div>
                                    <div class="chatpane inner-content ">
                                        <div class="settings-list">
                                            <div class="setting-item">
                                                <div class="setting-text">Sidebar Position</div>
                                                <div class="setting-set">
                                                    <select class="sidebar-pos-option form-control input-inline input-sm input-small ">
                                                        <option value="left" selected="selected">Left</option>
                                                        <option value="right">Right</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Header</div>
                                                <div class="setting-set">
                                                    <select class="page-header-option form-control input-inline input-sm input-small ">
                                                        <option value="fixed" selected="selected">Fixed</option>
                                                        <option value="default">Default</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Sidebar Menu </div>
                                                <div class="setting-set">
                                                    <select class="sidebar-menu-option form-control input-inline input-sm input-small ">
                                                        <option value="accordion" selected="selected">Accordion</option>
                                                        <option value="hover">Hover</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Footer</div>
                                                <div class="setting-set">
                                                    <select class="page-footer-option form-control input-inline input-sm input-small ">
                                                        <option value="fixed">Fixed</option>
                                                        <option value="default" selected="selected">Default</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="chat-header">
                                            <h5 class="list-heading">Account Settings</h5>
                                        </div>
                                        <div class="settings-list">
                                            <div class="setting-item">
                                                <div class="setting-text">Notifications</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-1">
                                                            <input type = "checkbox" id = "switch-1" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Show Online</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-7">
                                                            <input type = "checkbox" id = "switch-7" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Status</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-2">
                                                            <input type = "checkbox" id = "switch-2" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">2 Steps Verification</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-3">
                                                            <input type = "checkbox" id = "switch-3" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="chat-header">
                                            <h5 class="list-heading">General Settings</h5>
                                        </div>
                                        <div class="settings-list">
                                            <div class="setting-item">
                                                <div class="setting-text">Location</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-4">
                                                            <input type = "checkbox" id = "switch-4" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Save Histry</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-5">
                                                            <input type = "checkbox" id = "switch-5" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="setting-item">
                                                <div class="setting-text">Auto Updates</div>
                                                <div class="setting-set">
                                                    <div class="switch">
                                                        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-6">
                                                            <input type = "checkbox" id = "switch-6" 
                                                                   class = "mdl-switch__input" checked>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end chat sidebar -->
            </div>
            <!-- end page container -->
            <!-- start footer -->
            <jsp:include page="../include/footer.jsp" />
            <!-- end footer -->
        </div>
        <!-- start js include path -->
        <jsp:include page="../include/js.jsp" />
        <!-- end js include path -->
    </body>

</html>