<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
  <link href="${root}/assets/css/kakaomap.css" rel="stylesheet"/>
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
  <!-- start album -->
  <div class="row">
    <div class="col text-end">
      <div id="map" style="width: 100%; height: 500px"></div>
    </div>
    <div class="col col-6">
      <div class="mb-3">
        <img src="http://placehold.it/400x200" class="img-fluid w-100 h-25" alt="...">
      </div>
      <div class="mb-3 row g-3">
        <div class="col-md-6">
          <label for="nickname" class="form-label">작성자</label>
          <input type="text" class="form-control" id="nickname" name="nickname" value="ssafy" disabled>
        </div>
        <div class="col-md-6">
          <label for="visitedDate" class="form-label">방문 날짜</label>
          <input type="text" class="form-control" id="visitedDate" name="visitedDate" value="2023-01-01" disabled>
        </div>
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">핫플레이스 이름</label>
        <input type="text" class="form-control" id="name" name="name" value="핫플레이스 이름" disabled>
      </div>
      <div class="mb-3">
        <label for="desc" class="form-label">핫플레이스 설명</label>
        <textarea class="form-control" id="desc" rows="10" disabled>핫플레이스 설명 주절 주절...</textarea>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
        <button class="btn btn-secondary" type="button">목록</button>
      </div>
      </form>
    </div>
  </div>
  <!-- end album -->
</main>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
<script type="text/javascript" src="${root}/assets/js/requestApi.js"></script>
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

    function selectAttraction(id, contentTypeId, title, addr1, zipcode, firstImage, latitude, longitude) {
        document.getElementById("contentId").setAttribute('value', id);
        document.getElementById("contentTypeId").setAttribute('value', contentTypeId);
        // 지도에 마커를 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(latitude, longitude)
        });
        console.log(title);
        // 커스텀 오버레이에 표시할 컨텐츠 입니다
        // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
        // 별도의 이벤트 메소드를 제공하지 않습니다
        var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">' +
            title +
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="' + firstImage + '" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="ellipsis">' + addr1 + '</div>' +
            '                <div class="jibun ellipsis">(우) ' + zipcode + '</div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

        var overlay = new kakao.maps.CustomOverlay({
            content: content,
            map: map,
            position: marker.getPosition()
        });

        overlay.setMap(map);
        setCenter(latitude, longitude);
    }

    function setCenter(latitude, longitude) {
        // 이동할 위도 경도 위치를 생성합니다
        var moveLatLon = new kakao.maps.LatLng(latitude, longitude);

        // 지도 중심을 이동 시킵니다
        map.setCenter(moveLatLon);
    }
</script>
</body>
</html>
