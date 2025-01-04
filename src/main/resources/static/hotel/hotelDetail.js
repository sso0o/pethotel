// 가져온 호텔 목록을 리스트에 추가하는 함수
function updateRoomList(rooms) {
    let roomListContainer = $('#roomListContainer');
    rooms.forEach(room => {
        let roomItem = `
        <div class="tm-recommended-place mb-4" id="room-${room.roomId}" style="min-height: 200px;">
            <div class="tm-room-image-box" style="min-width: 270px;"></div>
            <div class="tm-recommended-description-box">
                <h3 class="tm-recommended-title">${room.roomType}</h3>
                <p>
                    <i class="fa fa-user" aria-hidden="true"></i>
                    ${room.limitGuest} / 
                    <i class="fa fa-paw" aria-hidden="true"></i>
                    ${room.limitPet}
                </p>
                <p>체크인 : ${room.checkIn}<br>체크아웃 : ${room.checkOut}</p>
                <div>
                </div>
            </div>
            <a href="#" class="tm-recommended-price-box" style="position: relative;">
                <p class="tm-recommended-price" style="position: absolute; bottom: 10px;">${Number(room.roomPrice).toLocaleString()} <span style="font-size: 1rem;"> / 박</span></p>
            </a>
        </div>
        `;
        roomListContainer.append(roomItem);
        imgRender(room);
    });
}

// 처음 및 스크롤시 호텔목롤 요청하 함수
function loadMoreRooms(hotelId) {
    let searchData = JSON.parse(sessionStorage.getItem('searchData'));

    let data = {
        page: currentPage,
        size: 4,
        location: searchData.location,
        hotelType: searchData.hotelType,
        checkIn: searchData.checkIn,
        checkOut: searchData.checkOut,
        room: searchData.room,
        guest: searchData.guest,
        pet: searchData.pet
    };


    $.ajax({
        url: '/hotel/search/' + BigInt(hotelId),
        type: 'get',
        async: false,
        data: data, // 쿼리 파라미터로 전달
        success: function (result) {
            if (result.rooms.content.length > 0) {
                updateRoomList(result.rooms.content);
                currentPage++;
                isLoading = false;
            }
        }, error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText)
            alert(result.msg);
        }
    })
}

// 호텔의 이미지를 렌더링하는 함수
function imgRender(room) {

    if (room.roomPhotos.length > 0){
        let slickHtml = '<div class="room-images-slick img-div" >';
        room.roomPhotos.forEach(photo => {
            slickHtml += `
                    <div><img src="${photo.rimgUrl}" alt="객실 이미지" class="img-fluid"></div>
                `;
        });
        slickHtml += '</div>';

        // HTML 구조에 슬릭 HTML 추가
        let hotelImageContainer = $('#room-' + room.roomId + ' .tm-room-image-box');
        hotelImageContainer.html(slickHtml);  // 기존의 이미지를 덮어씌운다

        if ($('#room-' + room.roomId + ' .room-images-slick').not('.slick-initialized').length > 0) {
            $('#room-' + room.roomId + ' .room-images-slick').slick({
                slidesToShow: 1,
                slidesToScroll: 1,
                arrows: false,
                dots: false,
                autoplay: true,
                autoplaySpeed: 2000
            });
        }
    }
}