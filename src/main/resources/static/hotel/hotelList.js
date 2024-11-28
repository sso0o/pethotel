// 가져온 호텔 목록을 리스트에 추가하는 함수
function updateHotelList(hotels) {
    let hotelListContainer = $('#hotelListContainer');
    hotels.forEach(hotel => {
        let hotelItem = `
        <li class="mb-4" id="hotel-${hotel.hotelId}">
          <div class="hotel-detail-content">
            <a href="/hotelDetail">
              <div class="hotel-detail">
                <div class="hotel-detail-1">
                </div>
                <div class="hotel-detail-2">
                  ${hotel.hotelName}
                </div>
                <div class="hotel-detail-3">
                  ${hotel.minPrice}
                </div>
              </div>
            </a>
          </div>
        </li>
        `;
        hotelListContainer.append(hotelItem);
        // 이미지 렌더링 함수 호출
        imgRender(hotel.hotelId);
    });
}

// 처음 및 스크롤시 호텔목롤 요청하 함수
function loadMoreHotels() {
    let searchData = JSON.parse(sessionStorage.getItem('searchData'));
    let data = {
        page: currentPage,
        size: 4
    };

    $.ajax({
        url: '/hotel/search',
        type: 'get',
        async: false,
        data: data,
        success: function (result) {
            if (result.hotels.content.length > 0) {
                updateHotelList(result.hotels.content);
                currentPage++;
                isLoading = false;
            }
        },
        error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText)
            alert(result.msg);
        }
    })
}

// 호텔의 이미지를 렌더링하는 함수
function imgRender(hotelId) {
    $.ajax({
        url: '/hotel/img/'+BigInt(hotelId),
        async: false,
        type: 'get',
        success: function (result){
            if (result.hotelPhotos.length > 0){
                let slickHtml = '<div class="hotel-images-slick">';
                result.hotelPhotos.forEach(photo => {
                    slickHtml += `
                    <div><img src="./hotel/uploads/hotel/${photo.himgFile}" alt="호텔 이미지" class="img-fluid"></div>
                `;
                });
                slickHtml += '</div>';

                // HTML 구조에 슬릭 HTML 추가
                let hotelImageContainer = $('#hotel-' + hotelId + ' .hotel-detail-1');
                hotelImageContainer.html(slickHtml);  // 기존의 이미지를 덮어씌운다


            }

        },
        error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText)
            alert(result.msg);
        }
    })
}