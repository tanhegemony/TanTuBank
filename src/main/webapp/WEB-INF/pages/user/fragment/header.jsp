<%-- 
    Document   : header
    Created on : Sep 30, 2022, 8:55:58 AM
    Author     : TanHegemony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid header">
      <div class="row">
        <div class="col-2 logo">
            <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/resources/images/tantubankLogo.png" class="img-fluid" alt="" /></a>
        </div>
        <div class="col-7 menu1 mt-3">
          <ul class="nav">
            <li class="nav-item">
              <a class="nav-link" href="#">
                <img src="${pageContext.request.contextPath}/resources/images/icon/aboutus.png" class="img-fluid" alt="" />
                Giới thiệu</a
              >
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                data-toggle="dropdown"
                href="#"
                role="button"
                aria-expanded="false"
              >
                <img src="${pageContext.request.contextPath}/resources/images/icon/banker.png" class="img-fluid" alt="" /> Cá
                nhân</a
              >
              <div class="dropdown-menu personal">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/viewInternalTransfer">Chuyển tiền nội ngân hàng</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/viewExternalTransfer">Chuyển tiền liên ngân hàng</a>
              </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="_blank" href="${pageContext.request.contextPath}/loanCalculation">
                <i class="fas fa-calculator"></i> Tính khoản vay</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><i class="fas fa-phone"></i> Liên hệ</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <img
                  src="${pageContext.request.contextPath}/resources/images/icon/recruitment.png"
                  class="img-fluid"
                  alt=""
                />
                Tuyển dụng</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><i class="fas fa-map-marked-alt"></i> Địa điểm giao dịch</a
              >
            </li>
          </ul>
        </div>
        <div class="col-3 menu2 mt-2">
          <ul class="nav justify-content-end" style="font-size: 12px">
            <li class="nav-item mt-3">
              <b><i class="fas fa-university"></i> Ngân hàng trực tuyến: </b>
            </li>
            <li class="nav-item mt-1">
              <a class="nav-link" href="#">Đăng nhập</a>
            </li>
            <!-- <li class="nav-item dropdown dropleft">
                  <a class="nav-link dropdown-toggle" data-toggle="dropdown" 
                  href="#" role="button" aria-expanded="false" style="color: white;">
                  <img src="images/user-image/tanhegemony.jpg" style="width: 30px;height: 30px;border-radius: 100%;" class="img-fluid" alt="">
                </a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Manage Control</a>
                    <a class="dropdown-item" href="#">Manage Account Number</a>
                    <a class="dropdown-item" href="#">Manage Profile</a>
                    <a class="dropdown-item" href="#">Change Password</a>
                    <a class="dropdown-item" href="#">Logout</a>
                  </div>
                </li> -->
          </ul>
        </div>
      </div>
    </div>
