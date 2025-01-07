function drawBookingDetail(data) {
    let searchData = JSON.parse(sessionStorage.getItem('searchData'));

    let checkInDate = new Date(searchData.checkIn);  // 체크인 날짜
    let checkOutDate = new Date(searchData.checkOut);  // 체크아웃 날짜

    let timeDiff = checkOutDate.getTime() - checkInDate.getTime();
    let dayDiff = timeDiff / (1000 * 3600 * 24);  // 밀리초를 일수로 변환

    let roomDetail = $('#room');
    let roomType = roomDetail.data('room-type');
    let roomPrice = roomDetail.data('room-price');
    let limitGuest = roomDetail.data('room-limit-guest');
    let limitPet = roomDetail.data('room-limit-pet');
    let checkIn = roomDetail.data('room-check-in');
    let checkOut = roomDetail.data('room-check-out');

    let hotel = $('#hotel');
    let hotelName = hotel.data('hotel-name')

    let bookingDetail = $('#bookingDetail');
    let item = `
    <h2>${hotelName}</h2>
    <h3>${roomType} </h3>
    <p></p>
    <p>총 숙박 일 : ${searchData.room}객실 ${dayDiff}박 </p>
    <p>체크인 일자 : ${searchData.checkIn} ${checkIn}</p>
    <p>체크아웃 일자 : ${searchData.checkOut} ${checkOut}</p>
    <p>총 인원 : ${searchData.guest} / ${limitGuest}</p>
    <p>총 펫 : ${searchData.pet} / ${limitPet}</p>
    <p></p>
    <h6>총 결제 금액</h6>
    <h5 style="color: red; font-weight: bold;"> ${ (roomPrice * dayDiff * searchData.room).toLocaleString() } 원</h5>
    <hr/>
    <div class="content-box-bottom">
      <button type="button" class="btn btn-primary tm-btn tm-btn-search" id="btnBooking" >예약</button>
    </div>
    `;

    bookingDetail.append(item);

    // let priceBox = $('#priceBox');
    // let price = `
    // `;
    // priceBox.append(price);

}