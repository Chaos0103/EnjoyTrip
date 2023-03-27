<%--
  Created by IntelliJ IDEA.
  User: leeyr
  Date: 2023/03/27
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp"%>
  <link href="${root}/assets/css/mypage.css" rel="stylesheet" />
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp"%>
</header>
<!-- end header -->

<!-- start section -->

<div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="position-sticky pt-3 sidebar-sticky">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">
              <span data-feather="home" class="align-text-bottom"></span>
              <i class="bi bi-person"></i>
              마이페이지
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file" class="align-text-bottom"></span>
              내가 쓴 게시물
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart" class="align-text-bottom"></span>
              내가 등록한 핫플레이스
            </a>
          </li>
          <li class="nav-item mt-5">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart" class="align-text-bottom"></span>
              회원탈퇴
            </a>
          </li>
        </ul>
      </div>
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">


      <%@ include file="/member/mypage/myInfo.jsp"%>


    </main>
  </div>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp"%>
<!-- end footer -->

<%-- script --%>
</body>
</html>
