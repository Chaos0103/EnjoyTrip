<%--
  Created by IntelliJ IDEA.
  User: leeyr
  Date: 2023/03/27
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/common/head.jsp" %>
    <link href="${root}/assets/css/mypage.css" rel="stylesheet"/>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
    <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section -->
<div class="container">
    <div class="container-fluid">
        <div class="row">
            <%@ include file="/member/mypage/mypageNav.jsp" %>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 ms-5">

                <c:choose>
                    <c:when test="${currShow eq 'myPage'}">
                        <%@ include file="/member/mypage/myInfo.jsp" %>
                    </c:when>
                    <c:when test="${currShow eq 'modifyPw'}">
                        <%@ include file="/member/mypage/mypageChangePw.jsp" %>
                    </c:when>
<%--                    <c:when test="${currShow eq 'myPage'}">--%>
<%--                        <%@ include file="/member/mypage/myInfo.jsp" %>--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${currShow eq 'myPage'}">--%>
<%--                        <%@ include file="/member/mypage/myInfo.jsp" %>--%>
<%--                    </c:when>--%>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>



            </main>
        </div>
    </div>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->

<%-- script --%>
<script>
    document.querySelector("#btn-change-pw").addEventListener("click", function (){
        let form = document.querySelector("#change-pw-form");
        // alert("비밀번호가 변경되었습니다. ")
        form.setAttribute("action","${root}/member?action=modifyPw");
        form.submit();
    });
</script>
</body>
</html>
