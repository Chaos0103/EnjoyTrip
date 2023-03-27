<%--
  Created by IntelliJ IDEA.
  User: leeyr
  Date: 2023/03/25
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/common/head.jsp"%>
<%--    <link href="${root}/assets/css/common.member.css" rel="stylesheet"/>--%>
    <link href="${root}/assets/css/login.css" rel="stylesheet" />
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
    <%@include file="/common/header.jsp"%>
</header>
<!-- end header -->

<!-- start section -->
<div id="wrapper" class="text-center">
    <main class="form-signin w-100 m-auto">
        <form id="login-form" method="post" action="${root}/account?action=login">
<%--            <input type="hidden" value="login" name="action">--%>
            <img class="mb-4" src="${root}/assets/img/logo.png" alt="" width="280" height="280" />
            <h1 class="h3 mb-3 fw-normal">로그인</h1>

            <div class="form-floating">
                <input
                        type="text"
                        class="form-control"
                        id="userId"
                        name="userId"
                        placeholder="userId"
                />
                <label for="userId">아이디</label>
            </div>
            <div class="form-floating">
                <input
                        type="password"
                        class="form-control"
                        id="userPassword"
                        name="userPassword"
                        placeholder="userPassword"
                />
                <label for="userPassword">비밀번호</label>
            </div>

            <div class="checkbox mb-3">
                <label> <input type="checkbox" value="remember-me" /> Remember me </label>
            </div>
            <button id="btn-login" class="w-100 mb-3 btn btn-lg btn-primary"
                    type="submit">로그인</button>
            <button
                    type="button"
                    class="w-100 mb-3 btn btn-sm btn-outline-warning"
                    data-bs-toggle="modal"
                    data-bs-target="#findPasswordModal"
            >
                비밀번호 찾기
            </button>
        </form>
        <div class="pt-3">
            <a class="register" href="${root}/member?action=mvregister"
            >아직 계정이 없으신가요?<br />계정 만들기</a
            >
        </div>
    </main>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp"%>
<!-- end footer -->

<%-- script --%>
<%--<script>--%>
<%--    document.querySelector("#btn-login").addEventListener("click",function (){--%>
<%--        let form = document.querySelector("#login-form");--%>
<%--        console.log("로그인 클릭");--%>
<%--        form.setAttribute("action","${root}/account?action=login");--%>
<%--        form.submit();--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>


