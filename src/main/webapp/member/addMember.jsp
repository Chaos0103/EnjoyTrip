<%--
  Created by IntelliJ IDEA.
  User: leeyr
  Date: 2023/03/26
  Time: 12:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp"%>
  <link href="${root}/assets/css/common.member.css" rel="stylesheet"/>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp"%>
</header>
<!-- end header -->

<!-- start section -->
<div class="container">
  <div class="text-center">
    <div class="m-auto p-1" style="width: 200px">
      <img class="mb-4" src="${root}/assets/img/logo.png" alt="" width="200" height="200" />
    </div>
    <form id="form-join" method="POST" action="" class="register-form m-auto form-shadow mt-2 mb-5">
      <div class="mb-4 mt-1">
        <h1>회원가입</h1>
      </div>
      <input type="hidden" name="action" value="register">
      <div class="input-group has-validation">
        <div class="form-floating">
          <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" />
          <!-- 인풋 클래스랑 바로 위 div에 is-invalid 클래스 추가하면 빨간색됨 -->
          <label for="memberId">아이디</label>
        </div>
        <button class="btn btn-outline-secondary" onclick="duplicateLoginId()" type="button">
          중복확인
        </button>
        <div class="invalid-feedback">사용할 수 없는 아이디입니다.</div>
      </div>
      <div class="input-group mt-3 mb-3">
        <div class="form-floating me-1">
          <input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="memberPassword" />
          <label for="memberPassword">비밀번호</label>
        </div>
      </div>
      <div class="input-group mt-3 mb-3">
        <div class="form-floating me-1">
          <input
                  type="password"
                  class="form-control"
                  id="passwordCheck"
                  placeholder="passwordCheck"
                  onkeyup="checkPassword()"
          />
          <!-- 인풋 클래스에 is-invalid 클래스 추가하면 빨간색됨 -->
          <label for="passwordCheck">비밀번호확인</label>
          <div class="invalid-feedback">비밀번호를 확인하세요</div>
        </div>
      </div>
      <hr>
      <div class="input-group mb-3">
        <div class="form-floating me-1">
          <input type="text" class="form-control" id="memberName" name="memberName" placeholder="memberName" />
          <label for="memberName">이름</label>
        </div>
      </div>
      <div class="input-group mb-3">
        <label for="memberBirth" class="">주민번호</label>
        <input type="text" class="form-control" id="memberBirth" name="memberBirth" placeholder="앞자리" maxlength="6" />
        <span class="input-group-text">-</span>
        <input type="password" class="form-control" id="memberGender" name="memberGender" placeholder="뒷자리" maxlength="7" />
      </div>
      <div class="input-group mb-3">
        <div class="form-floating me-1">
          <input type="email" class="form-control" id="memberEmail" name="memberEmail" placeholder="memberEmail" />
          <label for="memberEmail">이메일</label>
        </div>
      </div>
      <div class="input-group mb-3">
        <div class="form-floating me-1">
          <input type="tel" class="form-control" id="memberPhone" name="memberPhone" placeholder="memberPhone" />
          <label for="memberPhone">전화번호</label>
        </div>
      </div>
      <div class="input-group mb-3">
        <div class="form-floating me-1">
          <input
                  type="text"
                  class="form-control"
                  id="memberNickname"
                  name="memberNickname"
                  placeholder="memberNickname"
          />
          <label for="memberNickname">별명</label>
        </div>
      </div>


<%--      <div class="input-group mb-3">--%>
<%--        <div class="form-floating me-1">--%>
<%--          <input type="date" class="form-control" id="memberBirth" name="memberBirth" placeholder="memberBirth" />--%>
<%--          <label for="memberBirth">생년월일</label>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--      <div class="input-group form-floating mb-3">--%>
<%--        <div class="col-md">--%>
<%--          <div class="form-floating">--%>
<%--            <select class="form-select" id="memberGender" name="memberGender">--%>
<%--              <!-- <option selected>성별</option> -->--%>
<%--              <option selected>선택</option>--%>
<%--              <option value="1">남성</option>--%>
<%--              <option value="2">여성</option>--%>
<%--              <option value="3">비공개</option>--%>
<%--            </select>--%>
<%--            <label for="memberGender">성별</label>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>

      <div class="start-100">
        <button type="button" id="btn-join" class="btn btn-primary" >가입하기</button>
      </div>
    </form>
  </div>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp"%>
<!-- end footer -->
<script src="./assets/js/index.js"></script>
<script>
  document.querySelector("#btn-join").addEventListener("click",function (){
    let form = document.querySelector("#form-join");
    form.setAttribute("action","${root}/member?action=register");
    console.log("가입클릭");
    form.submit();
  });
</script>
</body>
</html>


