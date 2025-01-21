function cancelBooking(bookingId) {
    let confirmation = confirm("예약을 취소하시겠습니까?");
    if (confirmation) {
        // 사용자가 '확인'을 클릭한 경우
        $.ajax({
            url: '/mybooking/cancel/' + bookingId,
            type: 'put',
            success: function (response) {
                alert(response.msg);
                window.location.reload();
            },
            error: function (request, status, error) {
                let result = jQuery.parseJSON(request.responseText);
                alert(result.msg);
            }
        });
    }
}

function paymentBooking(bookingId) {

    let oPay = Naver.Pay.create({
        "mode" : "development", // development or production
        "clientId": "HN3GGCMDdTgGUfl0kFCo", // clientId
        "chainId": "Wms3cFptanlpSjR" // chainId
    });

    $.ajax({
        url: '/mybooking/' + bookingId.toString(),
        type: 'get',
        success: function (data) {
            oPay.open({
                "merchantPayKey": "20241207WWKEnn",
                // "productName": hotelDetail.data('hotel-name')+" - "+roomDetail.data('room-type'),
                "productName": data.hotel.hotelName + " - "+data.room.roomName,
                "productCount": "1",
                "totalPayAmount": data.booking.totalPrice,
                "taxScopeAmount": data.booking.totalPrice,
                "taxExScopeAmount": "0",
                "returnUrl": "http://localhost:8081/booking/complete?bookingId="+bookingId.toString(),
            });
        },
        error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText);
            alert(result.msg);
        }
    })

}