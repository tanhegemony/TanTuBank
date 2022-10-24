<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/style/fontawesome-free-6.2.0-web/css/all.min.css" />" />
<div class="sidebar-container">
    <div class="sidemenu-container navbar-collapse collapse fixed-menu">
        <div id="remove-scroll">
            <ul class="sidemenu page-header-fixed p-t-20" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <li class="sidebar-toggler-wrapper hide">
                    <div class="sidebar-toggler">
                        <span></span>
                    </div>
                </li>
                <li class="sidebar-user-panel">
                    <div class="user-panel">
                        <div class="row">
                            <div class="sidebar-userpic">
                                <img src="${pageContext.request.contextPath}/resources-management/assets/img/dp.jpg" class="img-responsive" alt=""> </div>
                        </div>
                        <div class="profile-usertitle">
                            <div class="sidebar-userpic-name"> John Deo </div>
                            <div class="profile-usertitle-job"> Manager </div>
                        </div>
                        <div class="sidebar-userpic-btn">
                            <a class="tooltips" href="user_profile.html" data-placement="top" data-original-title="Profile">
                                <i class="material-icons">person_outline</i>
                            </a>
                            <a class="tooltips" href="email_inbox.html" data-placement="top" data-original-title="Mail">
                                <i class="material-icons">mail_outline</i>
                            </a>
                            <a class="tooltips" href="chat.html" data-placement="top" data-original-title="Chat">
                                <i class="material-icons">chat</i>
                            </a>
                            <a class="tooltips" href="login.html" data-placement="top" data-original-title="Logout">
                                <i class="material-icons">input</i>
                            </a>
                        </div>
                    </div>
                </li>
                <li class="menu-heading">
                    <span>-- Main</span>
                </li>
                <%--<sec:authorize access="isAuthenticated()">--%>
                <li class="nav-item <c:if test="${action == 'dashboard'}">active</c:if>">
                        <a href="index.html" class="nav-toggle">
                            <i class="material-icons">dashboard</i>
                            <span class="title">Bảng điều khiển</span>

                        </a>

                    </li>
                <%--<sec:authorize access="hasRole('ADMIN')">--%>
                <li class="menu-heading m-t-20">
                    <span>--Admin</span>
                </li>

                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">account_box</i>
                        <span class="title">Quản lý người dùng</span>
                    </a>
                </li>
                <%--</sec:authorize>--%>
                <%--<sec:authorize access="hasRole('TELLER')">--%>
                <li class="menu-heading m-t-20">
                    <span>--Teller</span>
                </li>
                <li class="nav-item <c:if test="${action == 'deposit_for_customer'}">active</c:if>">
                    <a href="${pageContext.request.contextPath}/management/depositForCustomer" class="nav-link nav-toggle">
                        <i class="material-icons">attach_money</i>
                        <span class="title">Nạp tiền cho khách hàng</span>
                    </a>
                </li>
                <li class="nav-item start <c:if test="${action == 'managePaymentBankAccount' || action == 'manageSavingBankAccount' || action == 'manageExternalBankAccount'}">active</c:if>">
                        <a class="nav-link nav-toggle">
                            <i class="material-icons">account_balance</i>
                            <span class="title">Account Banking</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>
                        <ul class="sub-menu">
                            <li class="nav-item <c:if test="${nav == 'managePaymentBankAccount'}">active</c:if>">
                            <a href="${pageContext.request.contextPath}/management/viewManageBankAccount?action=viewBankAccounts&nav=managePaymentBankAccount" class="nav-link ">
                                <span class="title"><i class="fa-solid fa-credit-card"></i> Thanh toán</span>
                            </a>
                        </li>
                        <li class="nav-item <c:if test="${nav == 'manageSavingBankAccount'}">active</c:if>">
                            <a href="${pageContext.request.contextPath}/management/viewManageBankAccount?action=viewBankAccounts&nav=manageSavingBankAccount" class="nav-link ">
                                <span class="title"><i class="fas fa-piggy-bank"></i> Tiết kiệm</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item <c:if test="${nav == 'manageExternalBankAccount'}">active</c:if>">
                            <a href="${pageContext.request.contextPath}/management/viewManageBankAccount?action=viewBankAccounts&nav=manageExternalBankAccount" class="nav-link ">
                                <span class="title"><i class="fa-solid fa-earth-americas"></i> Liên ngân hàng</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item <c:if test="${nav == 'manageUnActiveBankAccount'}">active</c:if>">
                            <a href="${pageContext.request.contextPath}/management/viewManageBankAccount?action=viewBankAccounts&nav=manageUnActiveBankAccount" class="nav-link ">
                                <span class="title"><i class="fa-solid fa-toggle-off"></i> Chưa hoạt
                                    động</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item <c:if test="${nav == 'openOrEditBankAccount'}">active</c:if>">
                            <a href="${pageContext.request.contextPath}/management/viewOpenOrEditBankAccount" class="nav-link ">
                                <span class="title"><i class="fa-solid fa-plus"></i> Mở tài khoản</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">autorenew</i>
                        <span class="title">Quản lý giao dịch</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons"> person</i>
                        <span class="title">Quản lý Khách hàng</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="material-icons">assignment_returned</i>
                        <span class="title">Rút tiền</span>
                    </a>
                </li>
                <%--</sec:authorize>--%>
                <%--</sec:authorize>--%>
            </ul>
        </div>
    </div>
</div>