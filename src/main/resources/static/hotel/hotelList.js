// 가져온 호텔 목록을 리스트에 추가하는 함수
function updateHotelList(hotels) {
    let hotelListContainer = $('#hotelListContainer');
    hotels.forEach(hotel => {
        let facilities = hotel.hotelFacilities ? hotel.hotelFacilities.split(", ") : [];
        let facilityChips = facilities.length > 0 ?
            facilities.map(facility => {
                return `<span class="chip">${facility}</span>`;
            }).join(' ') : ``;
        let hotelItem = `
            <div class="tm-recommended-place mb-4" id="hotel-${hotel.hotelId}" style="min-height: 200px;">
              <div class="tm-hotel-image-box" style="min-width: 270px;"></div>
<!--              <img src="img/tm-img-06.jpg" alt="Image" class="img-fluid tm-recommended-img">-->
              <div class="tm-recommended-description-box">
                <h3 class="tm-recommended-title">${hotel.hotelName}</h3>
                <div></div>
                <div>
                  ${facilityChips} 
                </div>
<!--                <p class="tm-text-gray">Sed egestas, odio nec bibendum mattis, quam odio hendrerit risus, eu varius eros-->
<!--                  lacus sit amet lectus. Donec blandit luctus dictum...</p>-->
              </div>
              <a href="/hotel/hotelDetail/${hotel.hotelId}" class="tm-recommended-price-box" style="position: relative;">
                <p class="tm-recommended-price" style="position: absolute; bottom: 10px;">${Number(hotel.minPrice).toLocaleString()} ~</p>
              </a>
            </div>    
        `;
        hotelListContainer.append(hotelItem);
        // 이미지 렌더링 함수 호출
        imgRender(hotel.hotelId);
    });
}

// <li className="mb-4" id="hotel-${hotel.hotelId}">
//     <div className="hotel-detail-content">
//         <a href="/hotel/hotelDetail/${hotel.hotelId}">
//             <div className="hotel-detail">
//                 <div className="hotel-detail-1">
//                 </div>
//                 <div className="hotel-detail-2">
//                     ${hotel.hotelName}
//                 </div>
//                 <div className="hotel-detail-3">
//                     ~ ${Number(hotel.minPrice).toLocaleString()} 부터 <!-- minPrice를 천 단위 콤마로 포맷 -->
//                 </div>
//             </div>
//         </a>
//     </div>
// </li>

// 처음 및 스크롤시 호텔목롤 요청하 함수
function loadMoreHotels() {
    let searchData = JSON.parse(sessionStorage.getItem('searchData'));
    let data = {
        page: currentPage,
        size: 4,
        filter: $('#inputFilterChips').val(),
        filterSize: $('#inputFilterSize').val() ? parseInt($('#inputFilterSize').val(), 10): 0,
        location: searchData.location,
        checkIn: searchData.checkIn,
        checkOut: searchData.checkOut,
        room: searchData.room,
        guest: searchData.guest,
        pet: searchData.pet
    };

    $.ajax({
        url: '/hotel/SearchFilter',
        type: 'get',
        async: false,
        data: data,
        success: function (result) {
            if (result.hotels.length > 0) {
                updateHotelList(result.hotels);
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
        url: '/hotel/img/' + BigInt(hotelId),
        async: false,
        type: 'get',
        success: function (result) {
            if (result.hotelPhotos.length > 0) {
                let slickHtml = '<div class="hotel-images-slick img-div" >';
                result.hotelPhotos.forEach(photo => {
                    slickHtml += `
                    <div><img src="http://localhost:8081/uploads/hotel/${photo.himgFile}" alt="호텔 이미지" class="img-fluid" style="width: 270px; height: 230px;"></div>
                `;
                });
                slickHtml += '</div>';

                // HTML 구조에 슬릭 HTML 추가
                let hotelImageContainer = $('#hotel-' + hotelId + ' .tm-hotel-image-box');
                hotelImageContainer.html(slickHtml);  // 기존의 이미지를 덮어씌운다

                if ($('#hotel-' + hotelId + ' .hotel-images-slick').not('.slick-initialized').length > 0) {
                    $('#hotel-' + hotelId + ' .hotel-images-slick').slick({
                        slidesToShow: 1,
                        slidesToScroll: 1,
                        arrows: false,
                        dots: false,
                        autoplay: true,
                        autoplaySpeed: 2000
                    });
                }
            }

        },
        error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText)
            alert(result.msg);
        }
    })
}