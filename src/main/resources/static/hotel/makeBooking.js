function drawBookingDetail(data) {
    let searchData = JSON.parse(sessionStorage.getItem('searchData'));

    let checkInDate = new Date(searchData.checkIn);  // 체크인 날짜
    let checkOutDate = new Date(searchData.checkOut);  // 체크아웃 날짜

    let timeDiff = checkOutDate.getTime() - checkInDate.getTime();
    let dayDiff = timeDiff / (1000 * 3600 * 24);  // 밀리초를 일수로 변환

    let roomDetail = $('#room');
    let roomPrice = roomDetail.data('room-price');
    let limitGuest = roomDetail.data('room-limit-guest');
    let limitPet = roomDetail.data('room-limit-pet');

    let bookingDetail = $('#bookingDetail');
    let item = `
    <p>체크인 일자 : ${searchData.checkIn}</p>
    <p>체크아웃 일자 : ${searchData.checkOut}</p>
    <p>총 인원 : ${searchData.guest} / ${limitGuest}</p>
    <p>총 펫 : ${searchData.pet} / ${limitPet}</p>
    <p>총 가격 : ${ (roomPrice * dayDiff).toLocaleString() } 원</p>
    <p>총 일수 : ${dayDiff} 일</p>
    <p></p>
    `;

    bookingDetail.append(item);

}