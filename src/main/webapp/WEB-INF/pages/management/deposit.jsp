<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Nạp tiền vào tài khoản khách hàng</title>
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<c:url value="/resources/style/fontawesome-free-6.2.0-web/css/all.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources-management/assets/css/deposit.css" />" />
        <link rel="stylesheet" href="<c:url value="/resources-management/assets/responsive/deposit_responsive.css" />" />
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
                                    <div class="page-title">Nạp tiền vào tài khoản cho khách hàng</div>
                                </div>
                                <ol class="breadcrumb page-breadcrumb pull-right">
                                    <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="${pageContext.request.contextPath}/home_manage">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                    </li>
                                    <c:if test="${makeDeposit == true}">
                                        <li class="active">Nạp tiền vào tài khoản cho khách hàng</li>
                                        </c:if>
                                        <c:if test="${completeDeposit == true}">
                                        <li><a class="parent-item" href="${pageContext.request.contextPath}/home_manage">
                                                Nạp tiền vào tài khoản cho khách hàng</a>&nbsp;<i class="fa fa-angle-right"></i>
                                        </li>
                                        <li class="active">Hoàn thành giao dịch</li>
                                        </c:if>

                                </ol>
                            </div>
                        </div>
                        <!-- chart start -->
                        <c:if test="${makeDeposit == true}">
                            <div class="row makeDeposit">
                                <div class="col-md-12">
                                    <form action="${pageContext.request.contextPath}/management/resultMakeDeposit" method="post">
                                        <div class="form-group">
                                            <label for="depositAccountNumber">Nhập tài khoản nạp: </label>
                                            <input type="text" class="form-control" name="depositAccountNumber" id="depositAccountNumber"  
                                                   placeholder="Số tài khoản nào cần nạp" value="${depositAccountNumber}" onchange="this.form.submit();">
                                            <small class="form-text text-muted">${messageReceiveAccountNumber}</small>
                                        </div>
                                        <div class="row customerInfoDeposit">
                                            <div class="col-4 nameCus">
                                                <div class="form-group">
                                                    <label>Tên tài khoản nạp: </label>
                                                    <input type="text" class="form-control" value="${receiveBankAccount.accountName}" readonly>
                                                </div>
                                            </div>
                                            <div class="col-4 emailCus">
                                                <div class="form-group">
                                                    <label>Email khách hàng: </label>
                                                    <input type="text" class="form-control" value="${receiveBankAccount.customer.customerEmail}" readonly>
                                                </div>
                                            </div>
                                            <div class="col-4 phoneCus">
                                                <div class="form-group">
                                                    <label>Số điện thoại khách hàng: </label>
                                                    <input type="text" class="form-control" value="${receiveBankAccount.customer.customerPhone}" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="balanceDeposit">Nhập số tiền nạp: </label>
                                            <input type="text" class="form-control" name="balanceDeposit" id="balanceDeposit"  
                                                   placeholder="Số tài khoản nào cần nạp" value="${balanceTransactionString}">
                                            <small class="form-text text-muted">${messageBalanceTransaction}</small>
                                            <script>
                                                var Amount = document.getElementById("balanceDeposit");
                                                Amount.addEventListener('keyup', function (evt) {
                                                    var n = parseFloat(this.value.replace(/\D/g, ''), 10);
                                                    Amount.value = n.toLocaleString();
                                                }, false);
                                            </script>
                                        </div>
                                        <div class="row bank">
                                            <div class="col-3">
                                                <div class="form-group">
                                                    <label>Ngân hàng: </label>
                                                    <input type="text" class="form-control" value="TanTuBank" readonly>
                                                </div>
                                            </div>
                                            <div class="col-9">
                                                <div class="form-group">
                                                    <label for="tantuBankAddress">Địa chỉ ngân hàng: </label>
                                                    <select class="form-control" name="tantuBankAddress" id="tantuBankAddress" required>
                                                        <c:forEach var="tantubankAddress" items="${tantubankAddressList}">
                                                            <c:if test="${tantubankAddress.address == sessionScope.tantuBankAddress}">
                                                                <option value="${tantubankAddress.address}" selected>${tantubankAddress.address}</option>
                                                            </c:if>
                                                            <c:if test="${tantubankAddress.address != sessionScope.tantuBankAddress}">
                                                                <option value="${tantubankAddress.address}">${tantubankAddress.address}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row staffInfoDeposit">
                                            <div class="col-6">
                                                <div class="form-group">
                                                    <label for="staffId">Nhập Id Nhân viên thực hiện : </label>
                                                    <input type="number" class="form-control" name="staffId" id="staffId"  
                                                           placeholder="Nhân viên nào thực hiện?" value="${staffId}" onchange="this.form.submit();">
                                                    <small class="form-text text-muted">${messageStaff}</small>
                                                </div>    
                                            </div>
                                            <div class="col-6">
                                                <div class="form-group">
                                                    <label>Tên nhân viên: </label>
                                                    <input type="text" class="form-control" value="${staff.staffName}" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row depositButton text-center">
                                            <div class="col">
                                                <button name="depositButton" class="btn">
                                                    <img src="${pageContext.request.contextPath}/resources-management/images/icon/deposit.png" class="img-fluid" alt=""> Nạp tiền</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${completeDeposit == true}">
                            <div class="row completeDeposit justify-content-center">
                                <div class="col-6">
                                    <h5 class="headerCD text-center">Thông tin hoá đơn</h5>
                                    <div class="row">
                                        <div class="col">
                                            <table class="table table-borderless">
                                                <tr>
                                                    <td><p>Số tài khoản:</p></td>
                                                    <td><b>${sessionScope.transaction.bankAccount2.accountNumber}</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Tên tài khoản: </p></td>
                                                    <td><b>${sessionScope.transaction.bankAccount2.accountName}</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Số tiền nạp: </p></td>
                                                    <td><b><fmt:formatNumber type="number" value="${sessionScope.transaction.transactionAmount}" />đ</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Phí giao dịch: </p></td>
                                                    <td><b>2.000đ</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Tổng tiền giao dịch: </p></td>
                                                    <td><b><fmt:formatNumber type="number" value="${sessionScope.transaction.transactionAmount + 2000}" />đ</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Ngân hàng giao dịch: </p></td>
                                                    <td><b>TanTuBank</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Địa chỉ ngân hàng: </p></td>
                                                    <td><b>${sessionScope.transaction.tantuBankAddress}</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Nhân viên thực hiện: </p></td>
                                                    <td><b>${sessionScope.transaction.staff.staffName}</b></td>
                                                </tr>
                                                <tr>
                                                    <td><p>Ngày giao dịch: </p></td>
                                                    <td><b><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sessionScope.transaction.transactionDate}" /></b></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2"><button onclick="javascript:window.print();" class="btn"><i class="fa-solid fa-print"></i> In hoá đơn</button></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <button onclick="location.href = '${pageContext.request.contextPath}/management/depositForCustomer'" class="nextTransaction btn mt-4"><i class="fa-solid fa-forward"></i> Tiếp tục giao dịch</button>
                                            <button onclick="location.href = '${pageContext.request.contextPath}/admin/adminHome'" class="backHome btn btn-danger mt-4"><i class="fa-solid fa-backward"></i> Quay lại trang chủ</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

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