
function bookingApprove(roomDetailId, bookingId){
    let selectMyHotels = $('#myHotels').val();
    if (bookingId){
        if(confirm("예약을 해당 객실로 배정하시겠습니까?"))
            $.ajax({
                url: '/manager/booking/approve/'+ bookingId+'/' +roomDetailId,
                type: 'PUT',
                success: function(response) {
                    alert(response.msg);
                    console.log(response)
                    location.href = "/manager/myhotelrequest/"+selectMyHotels
                },
                error: function (request, status, error) {
                    let result = jQuery.parseJSON(request.responseText)
                    alert(result.msg);
                }

            })
    }

}
