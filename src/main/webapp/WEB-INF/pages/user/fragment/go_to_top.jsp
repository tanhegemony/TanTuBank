<%-- 
    Document   : go_to_top
    Created on : Sep 30, 2022, 8:56:20 AM
    Author     : TanHegemony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<button
      style="
        position: fixed;
        bottom: 20px;
        right: 30px;
        z-index: 99;
        font-size: 18px;
        border: none;
        outline: none;
        background-color: #72c673;
        color: black;
        cursor: pointer;
        padding: 15px;
        border-radius: 4px;
      "
      onclick="topFunction()"
      id="scroll"
      title="Go to top"
    >
      <i class="fas fa-arrow-up"></i>
    </button>
    <script>
      var myScroll = document.getElementById("scroll");
      window.onscroll = function () {
        scrollFunction();
      };

      function scrollFunction() {
        if (
          document.body.scrollTop > 20 ||
          document.documentElement.scrollTop > 20
        ) {
          myScroll.style.display = "block";
        } else {
          myScroll.style.display = "none";
        }
      }
      function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
      }
    </script>
