<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <li class="nav-item active">
                                <a href="index.html" class="nav-toggle">
                                    <i class="material-icons">dashboard</i>
                                    <span class="title">Bảng điều khiển</span>

                                </a>

                            </li>
                            <li class="menu-heading m-t-20">
                                <span>--Admin</span>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link nav-toggle">
                                    <i class="material-icons">account_box</i>
                                    <span class="title">Quản lý người dùng</span>
                                </a>
                            </li>
                            <li class="menu-heading m-t-20">
                                <span>--Teller</span>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link nav-toggle">
                                    <i class="material-icons">attach_money</i>
                                    <span class="title">Gửi tiền</span>
                                </a>
                            </li>

                            <li class="nav-item">
                                <a href="#" class="nav-link nav-toggle">
                                    <i class="material-icons">account_circle</i>
                                    <span class="title">Quản lý tài khoản</span>
                                </a>
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
                        </ul>
                    </div>
                </div>
            </div>