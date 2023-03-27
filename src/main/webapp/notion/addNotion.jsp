<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
</head>
<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section -->
<form method="post" action="${pageContext.request.contextPath}/notion?action=write">
  <div class='shadow m-lg-auto m-lg-5 m-5 m-auto p-lg-5 container-sm justify-content-center align-content-center'>
    <div class='notion-head m-auto'>
      <div class='notion-title'>
        <div class="mb-3 ">
          <div class="input-group ">
            <span class="input-group-text" id="basic-addon2">제목</span>
            <input type="text" class="form-control" placeholder="제목을 입력하세요" aria-label="제목을 입력하세요"
                   name="title"
                   aria-describedby="basic-addon2">
          </div>
        </div>
      </div>
    </div>
    <div class='notion-body'>
      <div>
        <div class=' notion-content'>
          <div class="mb-3">
            <div class="input-group">
                    <textarea type="text" name="content" class="form-control" id="basic-url"
                              aria-describedby="basic-addon3"
                              aria-label="With textarea" rows='20'></textarea>
            </div>
            <div class="form-text">내용을 입력하세요</div>
          </div>
        </div>
      </div>
    </div>
    <div class='notion-footer mt-5 '>
      <button type="submit" class='btn btn-outline-success'>등록하기</button>
    </div>
  </div>
</form>
<div class='m-5 m-auto p-lg-5 container-sm justify-content-center align-content-center'>
  <div class='row justify-content-end'>
    <a href='${root}?action=list' type='button' class=' col-1 btn btn-outline-info'>목록으로</a>
  </div>
</div>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
</body>
</html>
