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
<!-- start section -->
<main class="container">
  <div class="p-4 p-md-5 mb-4 text-secondary rounded bg-white shadow">
    <div class="col-md-6 px-0">
      <h1 class="display-5 fst-italic">핫플 인증</h1>
      <p class="lead my-3">방문했던 핫플레이스를 자랑하세요!</p>
    </div>
  </div>
  <!-- start album -->

  <div class="row">
    <div class="col text-end">
      <form class="row mb-3">
        <div class="input-group mb-3">
          <input type="text" id="attracttionName" class="form-control" placeholder="관광지 검색" aria-label="관광지 검색" aria-describedby="button-addon2"/>
          <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="searchAttraction()">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16" >
              <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
          </button>
        </div>
      </form>
      <div id="map" style="width: 100%; height: 500px"></div>
    </div>
    <form class="col col-6" action="${root}/hotPlace?action=write" method="post" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="uploadFileName" class="form-label">인증샷</label>
        <input type="file" class="form-control" id="uploadFileName">
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">나만의 핫플레이스 이름</label>
        <input type="text" class="form-control" id="name">
      </div>
      <div class="mb-3">
        <label for="visitedDate" class="form-label">방문 날짜</label>
        <input type="date" class="form-control" id="visitedDate">
      </div>
      <div class="mb-3">
        <label for="desc" class="form-label">핫플레이스 설명</label>
        <textarea class="form-control" id="desc" rows="3"></textarea>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
        <button class="btn btn-primary me-md-2" type="submit">글쓰기</button>
        <button class="btn btn-secondary" type="button">목록</button>
      </div>
      <!-- <label for="contentTypeId">장소 유형</label>
      <select class="form-select" aria-label="contentTypeId" id="contentTypeId">
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
      </select> -->
    </form>
  </div>
  <!-- end album -->
</main>
<!-- end section -->
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">검색결과</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="attraction">
        <button type="button" class="w-100 btn btn-outline-dark text-start mb-3">
          <div>AHC 스파</div>
          <div>서울특별시 강남구 도산대로 237</div>
        </button>
        <button type="button" class="w-100 btn btn-outline-dark text-start mb-3">
          <div>신사동 가로수길</div>
          <div>서울특별시 강남구 신사동 일대</div>
        </button>
      </div>
    </div>
  </div>
</div>
<%-- end section --%>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=92031818da3bea1d2a0cd22686ab48ea">
</script>
<script>
    var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    function setCenter(x, y) {
        // 이동할 위도 경도 위치를 생성합니다
        var moveLatLon = new kakao.maps.LatLng(x, y);

        // 지도 중심을 이동 시킵니다
        map.setCenter(moveLatLon);
        // 마커가 표시될 위치입니다
        var markerPosition = new kakao.maps.LatLng(x, y);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition,
        });

        map.setLevel(4);
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
    }
</script>
</body>
</html>
