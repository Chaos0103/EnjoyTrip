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
<div class="container">
    <form class="row" onchange="getTourList()">
        <%--    <form class="row">--%>
        <div class="col-3">
            <select class="form-select" aria-label="sidoCode" id="sidoCode" onchange="getSigunguCode(this.value)">
                <c:forEach items="${sidos}" var="sido">
                    <option value="${sido.code}">${sido.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-3">
            <select class="form-select" aria-label="gugunCode" id="gugunCode">
                <c:forEach items="${guguns}" var="gugun">
                    <option value="${gugun.code}">${gugun.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-3">
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
        </div>
        <div class="col-3">
            <button
              class="btn btn-primary"
              type="button"
              data-bs-toggle="offcanvas"
              data-bs-target="#offcanvasScrolling"
              aria-controls="offcanvasScrolling">
                결과 열기
            </button>
        </div>
        <!-- getTourList() -->
    </form>
</div>
<!-- start map -->
<div class="mt-3">
    <div id="map" style="width: 100%; height: 600px"></div>
</div>
<!-- end map -->
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
                    <c:forEach items="${attractions}" var="attraction">
                        <div class="col">
                            <div class="card shadow-sm">
                                <img src="${attraction.firstImage}" alt=""/>
                                <div class="card-body">
                                    <h5 class="card-title">${attraction.title}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${attraction.addr1}</h6>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn -sm btn-outline-secondary">
                                                View
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end right bar -->
<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
  crossorigin="anonymous"
></script>
<script src="../assets/js/requestApi.js"></script>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=92031818da3bea1d2a0cd22686ab48ea">
</script>
<script>
    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(${attractions.get(0).latitude}, ${attractions.get(0).longitude}), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    window.onload = function () {
        getTourList();
    };

    function getTourList() {
        const sidoCode = document.getElementById("sidoCode").value;
        const gugunCode = document.getElementById("gugunCode").value;
        const contentTypeId = document.getElementById("contentTypeId").value;

        const url = 'http://localhost:8080/api/attraction?action=search&sidoCode=' + sidoCode + '&gugunCode=' + gugunCode + '&contentTypeId=' + contentTypeId;
        fetch(url)
          .then((response) => response.json())
          .then((response) => {marker(response), setCenter(response), createAttraction(response)});

    }

    function setCenter(response) {
        // 이동할 위도 경도 위치를 생성합니다
        let attraction = response.data[0];
        var moveLatLon = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);

        // 지도 중심을 이동 시킵니다
        map.setCenter(moveLatLon);
    }

    function marker(response) {
        let attractions = response.data;

        var positions = [];
        attractions.forEach(function (attraction) {
            let data = {
                title: attraction.title,
                latlng: new kakao.maps.LatLng(attraction.latitude, attraction.longitude)
            }
            positions.push(data)
        });

        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

        for (var i = 0; i < positions.length; i++) {

            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image: markerImage // 마커 이미지
            });
        }
    }
</script>
</body>
</html>
