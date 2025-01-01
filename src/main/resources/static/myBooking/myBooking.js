function loadMyBooking(){
    let userId = $('#user').data('user-id');

    $.ajax({
        url: '/mybooking/'+userId,
        type: 'get',
        success: function (result) {
            if (result.myBookings.length > 0) {
                drawBookingDetail(result.myBookings);
            }
        },
        error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText)
            alert(result.msg);
        }
    });
}

function drawBookingDetail(myBookings){
    let myBookingContainer = $('#myBookingContainer');
    myBookings.forEach(booking => {
        let bookingItem = `
            <div class="tm-recommended-place mb-4" id="booking-${booking.bookingId}" style="width: fit-content; margin: auto;">
              <div class="tm-recommended-description-box">
                <h3 class="tm-recommended-title">${booking.hotelName}</h3>
                <p> </p>
                <p> 예약 객실: ${booking.roomType} </p>
                <p> 예약 일자: ${booking.startDate} ~ ${booking.endDate}  총 ${booking.totalDate} 일</p>
                <p> 예약 인원: <i class="fa fa-user" aria-hidden="true"></i> ${booking.bookingGuest} /
                 <i class="fa fa-paw" aria-hidden="true"></i> ${booking.bookingPet}</p>
                <p> 가격: ${Number(booking.totalPrice).toLocaleString()}</p>
              </div>
              <div class="tm-recommended-price-box">
                <p class="tm-recommended-price">$110</p>
                <p class="tm-recommended-price-link">Continue Reading</p>
              </div>
            </div>    
        `;
        myBookingContainer.append(bookingItem);
    })
}