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
          <input type="text" class="form-control" placeholder="관광지 검색" aria-label="관광지 검색" aria-describedby="button-addon2">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasScrolling"
            onclick="getTourList()"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-search"
              viewBox="0 0 16 16"
            >
              <path
                d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
              />
            </svg>
          </button>
        </div>
      </form>
      <div id="map" style="width: 100%; height: 500px"></div>
    </div>
    <form class="col col-6" action="${root}/hotPlace?action=write" method="post" enctype="multipart/form-data">
      <label for="uploadFileName">인증샷</label>
      <input id="uploadFileName" name="uploadFileName" type="file" class="form-control mb-3"/>
      <label for="name">핫플 이름</label>
      <input id="name" name="name" type="text" class="form-control mb-3"/>
      <label for="visitedDate">방문 날짜</label>
      <input id="visitedDate" name="visitedDate" type="date" class="form-control mb-3"/>

      <label for="contentTypeId">장소 유형</label>
      <select class="form-select" aria-label="contentTypeId" id="contentTypeId">
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
      </select>

      <label for="desc">핫플 상세설명</label>
      <textarea
        class="form-control"
        placeholder="방문 후기"
        id="desc"
        name="desc"
        rows="5"
      ></textarea>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
        <button class="btn btn-primary me-md-2" type="submit">글쓰기</button>
        <button class="btn btn-secondary" type="button">목록</button>
      </div>
    </form>
  </div>
  <!-- end album -->
  <!-- start right bar -->

  <div
    class="offcanvas offcanvas-start"
    data-bs-scroll="true"
    data-bs-backdrop="false"
    tabindex="-1"
    id="offcanvasScrolling"
    aria-labelledby="offcanvasScrollingLabel"
  >
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasScrollingLabel">검색 결과</h5>
      <button
        type="button"
        class="btn-close"
        data-bs-dismiss="offcanvas"
        aria-label="Close"
      ></button>
    </div>
    <div class="offcanvas-body">
      <div class="album py-5">
        <div class="container">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3" id="tour-list">
            <!-- 관광지 정보 비동기 통신 -->
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end right bar -->
</main>
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
