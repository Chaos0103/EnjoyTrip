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

<%-- start section --%>
<main class="p-3 mb-3 border-bottom container-sm">
  <div class="container-sm">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <form class="row g-3" method="get" action="${root}/hotPlace?action=list">
        <input type="hidden" name="action" value="list">
        <div class="col-auto">
          <input type="text" class="form-control" id="condition" name="condition" placeholder="작성자, 제목, 내용">
        </div>
        <div class="col-auto">
          <select class="form-select" name="select">
            <option value="1" selected>최신등록순</option>
            <option value="2">보기순</option>
            <option value="3">추천순</option>
          </select>
        </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-secondary mb-3">검색</button>
          <button type="button" class="btn btn-primary mb-3" onclick="location.href='/hotPlace?action=mvwrite'">글쓰기</button>
        </div>
      </form>
    </div>
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-sm-start">
      <div class="row row-cols-1 row-cols-md-4 g-4">
        <div class="col">
          <div class="card h-100">
            <img src="http://placehold.it/200x150" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">핫플레이스</h5>
              <p class="card-text text-truncate">This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit longer.</p>
              <div>
                <img src="icon-num-play.png" alt="" class="" style="size: 20px;"/> 1
              </div>
              <button type="button" class="btn btn-primary">더보기</button>
            </div>
            <div class="card-footer">
              <small class="text-body-secondary">Last updated 3 mins ago</small>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <img src="http://placehold.it/200x150" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">핫플레이스</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit longer.</p>
              <div>
                <img src="icon-num-play.png" alt="" class="" style="size: 20px;"/> 1
              </div>
              <button type="button" class="btn btn-primary">더보기</button>
            </div>
            <div class="card-footer">
              <small class="text-body-secondary">Last updated 3 mins ago</small>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <img src="http://placehold.it/200x150" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">핫플레이스</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit longer.</p>
              <div>
                <img src="icon-num-play.png" alt="" class="" style="size: 20px;"/> 1
              </div>
            </div>
            <div class="card-footer">
              <small class="text-body-secondary">Last updated 3 mins ago</small>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <img src="http://placehold.it/200x150" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">핫플레이스</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit longer.</p>
              <div>
                <img src="icon-num-play.png" alt="" class="" style="size: 20px;"/> 1
              </div>
            </div>
            <div class="card-footer">
              <small class="text-body-secondary">Last updated 3 mins ago</small>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <img src="http://placehold.it/200x150" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">핫플레이스</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit longer.</p>
              <div>
                <img src="icon-num-play.png" alt="" class="" style="size: 20px;"/> 1
              </div>
            </div>
            <div class="card-footer">
              <small class="text-body-secondary">Last updated 3 mins ago</small>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>
<%-- end section --%>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
</body>
</html>
