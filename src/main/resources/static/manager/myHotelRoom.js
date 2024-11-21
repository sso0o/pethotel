function addRoomModal(hotelId){
    if(hotelId === "add") {
        // 처음 초기화
        document.getElementById('roomForm').reset();
        // 추가모드
        $("#saveBtn").show();
        $("#modifyBtn").hide();
        $("#deleteBtn").hide();
    }else{
        // 수정 or 삭제모드
        $("#saveBtn").hide();
        $("#modifyBtn").show();
        $("#deleteBtn").show();

    }

    $('#errorDiv').hide();
    $('#errorMsg').text('');

    let myModal = new bootstrap.Modal(document.getElementById('roomModal'));
    myModal.show();
}


function saveRoom(type){
    let url;
    let data;
    let hotelId = $('#hotelId').val();
    let roomName = $('#roomName').val();
    let roomType = $('#roomType').val();
    let roomPrice = $('#roomPrice').val();
    let limitGuest = $('#limitGuest').val();
    let limitPet = $('#limitPet').val();
    let checkIn = $('#checkIn').val();
    let checkOut = $('#checkOut').val();
    let roomInfo = $('#roomInfo').val();

    if(type === "post"){
        url = "/manager/myroom";
    }else if (type === "put"){
        url = "/manager/myroom/"+BigInt(hotelId);
    }

    data = JSON.stringify({
        hotelId : hotelId,
        roomName: roomName,
        roomType : roomType,
        roomPrice : roomPrice,
        limitGuest : limitGuest,
        limitPet : limitPet,
        checkIn : checkIn,
        checkOut : checkOut,
        roomInfo : roomInfo
    })


    $.ajax({
        url: url,
        type: type,
        async: true,
        contentType: 'application/json',
        data: data,
        success: function (result){
            $('#errorDiv').hide();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');  // 오류 메시지 텍스트를 p 태그에 삽입
            alert(result.msg)
            location.href ='/manager/myhotelroom/'+hotelId;
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}