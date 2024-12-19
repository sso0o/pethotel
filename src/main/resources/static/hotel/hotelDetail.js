// 가져온 호텔 목록을 리스트에 추가하는 함수
function updateRoomList(rooms) {
    let roomListContainer = $('#roomListContainer');
    rooms.forEach(room => {
        let roomItem = `
        <li class="mb-4" id="room-${room.roomId}">
        <div class="room-container">
          <div class="row room-head-content">
            <div class="col">${room.roomType}</div>
           </div>
          <div class="row room-detail-content">
            <div class="room-detail">
                <div class="col-3 room-detail-1" >
                </div>
                <div class="col-4 room-detail-2">
                  <div style="margin: 10px">
                    ${room.roomType}
                  </div>
                </div>
                <div class="col-2 room-detail-3">
                  <div style="margin: 10px">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    ${room.limitGuest}<br>
                    <i class="fa fa-paw" aria-hidden="true"></i>
                    ${room.limitPet}
                  </div>
                </div>
                <div class="col-2 room-detail-4">
                  <div style="margin: 10px">
                    ${Number(room.roomPrice).toLocaleString()} <h6 style="color: #999999;">/ 박</h6>
                  </div>
                </div>
                <div class="col-1 room-detail-5">
                  <button type="button" class="btn btn-primary p-2" style="max-width: fit-content; margin: 10px" onclick="window.location.href='/booking/${room.roomId}'">
                  예약
                  </button>
                </div>
              </div>
          </div>
        </div>
        </li>
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
        let hotelImageContainer = $('#room-' + room.roomId + ' .room-detail-1');
        hotelImageContainer.html(slickHtml);  // 기존의 이미지를 덮어씌운다
    }
}