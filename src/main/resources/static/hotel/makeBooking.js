function drawBookingDetail(data) {
    let searchData = JSON.parse(sessionStorage.getItem('searchData'));

    let roomDetail = $('#room');
    let roomType = roomDetail.data('room-type');
    let roomPrice = roomDetail.data('room-price');
    let roomName = roomDetail.data('room-name');
    let limitGuest = roomDetail.data('room-limit-guest');
    let limitPet = roomDetail.data('room-limit-pet');
    let checkIn = roomDetail.data('room-check-in');
    let checkOut = roomDetail.data('room-check-out');

    let hotel = $('#hotel');
    let hotelName = hotel.data('hotel-name')

    let bookingDetail = $('#bookingDetail');
    let item = `
    <h2 style="font-family: 'NanumSquareNeo-Variable-bold', sans-serif">객실 예약</h2>
    <hr>
    <h2 style="font-family: 'NanumSquareNeo-Variable-bold', sans-serif">${hotelName}</h2>
    <h3 style="font-family: 'NanumSquareNeo-Variable-bold', sans-serif">${roomType} ${roomName} </h3>
    <br>
    <div class="row justify-content-center">
        <div class="col-3">
            <h5>총 숙박 일</h5>
        </div>
        <div class="col-3">
            <h5>${searchData.room}객실 ${searchData.dateDiff}박 </h5>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-3">
            <h5>체크인 일자</h5>
        </div>
        <div class="col-3">
            <h5>${searchData.checkIn} ${checkIn}</h5>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-3">
            <h5>체크아웃 일자</h5>
        </div>
        <div class="col-3">
            <h5>${searchData.checkOut} ${checkOut}</h5>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-3">
            <h5>총 인원</h5>
        </div>
        <div class="col-3">
            <h5>${searchData.guest} / ${limitGuest}</h5>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-3">
            <h5>총 펫</h5>
        </div>
        <div class="col-3">
            <h5>${searchData.pet} / ${limitPet}</h5>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6">
            <h4>총 금액</h4>
            <h5 style="font-family: 'NanumSquareNeo-Variable-bold', sans-serif; color: red">
            <span>${ (roomPrice * searchData.dateDiff * searchData.room).toLocaleString() }</span>원
            </h5>
            <hr>
            <button type="button" class="btn btn-primary tm-btn tm-btn-search" id="btnBooking" >예약</button>
        </div>
    </div>
    `;

    bookingDetail.append(item);
}