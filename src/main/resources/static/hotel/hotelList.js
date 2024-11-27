

// 호텔 목록을 추가하는 함수
function updateHotelList(hotels) {
    let hotelListContainer = $('#hotelListContainer');
    hotels.forEach(hotel => {
        let hotelItem =`
        <li class="mb-4">
          <div class="hotel-detail-content">
            <a href="/hotelDetail">
              <div class="hotel-detail">
                <div class="hotel-detail-1">
                  호텔 사진 들어갈고ㅓㅅ
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
    });
}

function loadMoreHotels(){
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
        success: function (result){
            if(result.hotels.content.length > 0){
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