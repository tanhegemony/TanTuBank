<%-- 
    Document   : footer
    Created on : Sep 30, 2022, 8:56:03 AM
    Author     : TanHegemony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<hr style="border: 2px solid black; margin-top: 5rem" />
    <div class="container-fluid footer">
      <div class="row">
        <div class="col-3 imgLogoFooter mt-3">
          <img src="${pageContext.request.contextPath}/resources/images/tantubankLogo.png" class="img-fluid" alt="" />
        </div>
        <div class="col-2 aboutUs mt-3">
          <h5>Giới thiệu</h5>
          <a href="#"><p>Lịch sử phát triển</p></a>
          <a href="#"><p>Tầm nhìn chiến lược</p></a>
        </div>
        <div class="col-3 supportInfo mt-3">
          <h5>Thông tin hỗ trợ</h5>
          <div class="row boss mt-3">
            <div class="col">
              <b>
                <img
                  src="${pageContext.request.contextPath}/resources/images/icon/user-at-phone.png"
                  class="img-fluid"
                  alt=""
                />
                Boss
              </b>
              <br />
              <a href="tel:0376160960">0376160960 (Anh Tấn)</a> <br />
              <a href="tel:0795768338">0795768338 (Tự Lol)</a>
            </div>
          </div>
          <div class="row contact mt-3">
            <div class="col">
              <b
                ><i class="fa-solid fa-phone"></i>
                Contact center 24/7
              </b>
              <br>
              <a href="#">2608061020022000</a>
            </div>
          </div>
          <div class="row email mt-3">
            <div class="col">
              <b
                ><i class="fa-solid fa-envelope"></i>
                Câu hỏi và phản hồi
              </b>
              <br>
              <a href="#">ngoctan10a2@gmail.com</a> <br>
              <a href="#">tuchicken@gmail.com</a>
            </div>
          </div>
        </div>
        <div class="col-2 info mt-3">
          <h5>Thông tin</h5>
          <a href="#"><p>Lãi suất</p></a>
          <a href="#"><p>ATM/Điểm giao dịch</p></a>
          <a href="#"><p>Khách hàng thân thiết</p></a>
        </div>
        <div class="col-2 mt-3 follow text-center">
          <h5>Theo dõi</h5>
          <p>
            <a href="#"
              ><img src="${pageContext.request.contextPath}/resources/images/icon/facebook.png" class="img-fluid" alt=""
            /></a>
            <a href="#"
              ><img src="${pageContext.request.contextPath}/resources/images/icon/instagram.png" class="img-fluid" alt=""
            /></a>
          </p>
        </div>
      </div>
      <div class="row text-center footer2">
        <div class="col">
          <hr style="border: 1px solid black; width: 50%" />
          <p>
            Copyright © 2022 by TANTUBANK (Vietnam) Ltd,. All rights reserved.
          </p>
        </div>
      </div>
    </div>
