function createAttraction(response) {
  let items = response.data;
  let content = "";

  items.forEach(function (item) {
    if (!item.firstImage) {
      item.firstImage = "${root}/assets/img/no-img.jpg";
    }

    content += `<div class="col">
                        <div class="card shadow-sm">
                            <img src="${item.firstImage}" alt="" />
                            <div class="card-body">
                                <h5 class="card-title">${item.title}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${item.addr1}</h6>                                
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn -sm btn-outline-secondary" onclick="setCenter(${item.latitude}, ${item.longitude})">View</button>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
  });
  document.getElementById("tour-list").innerHTML = content;
}

function getSigunguCode(sidoCode) {
  const url = `http://localhost:8080/api/attraction?action=gugun&sidoCode=${sidoCode}`;

  fetch(url)
    .then((response) => response.json())
    .then((response) => createSigunguCode(response));

  function createSigunguCode(response) {
    let items = response.data;
    let content = "";
    items.forEach(function (item) {
      content += `<option value="${item.code}">${item.name}</option>`
    });
    document.getElementById("gugunCode").innerHTML = content;
  }
}