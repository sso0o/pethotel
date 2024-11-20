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

        // $.ajax({
        //     url:'/admin/code/'+BigInt(codeId),
        //     type:'get',
        //     success: function (data){
        //         $('#codeId').val(data.id);
        //         $('#codeHead').val(data.codeHead);
        //         $('#codeName').val(data.codeName);
        //     }
        // })
    }
    let myModal = new bootstrap.Modal(document.getElementById('roomModal'));
    myModal.show();
}


function saveRoom(type){
    let url;

    if(type === "post"){
        url = "/manager/myroom";
    }else if (type === "put"){
        url = "";
    }

    let hotelId = $('#hotelId').val();
    let roomName = $('#roomName').val();
    let roomType = $('#roomType').val();
    let roomPrice = $('#roomPrice').val();
    let limitGuest = $('#limitGuest').val();
    let limitPet = $('#limitPet').val();
    let checkIn = $('#checkIn').val();
    let checkOut = $('#checkOut').val();
    let roomInfo = $('#roomInfo').val();


    let formData = new FormData();
    formData.append('hotelId', hotelId);
    formData.append('roomName', roomName);
    formData.append('roomType', roomType);
    formData.append('roomPrice', roomPrice);
    formData.append('limitGuest', limitGuest);
    formData.append('limitPet', limitPet);
    formData.append('checkIn', checkIn);
    formData.append('checkOut', checkOut);
    formData.append('roomInfo', roomInfo);

    // 파일 데이터를 FormData에 추가 (파일은 #hotelPhoto input을 사용)
    Array.from($('#roomPhotos')[0].files).forEach(function(file) {
        formData.append('roomPhotos', file);  // hotelPhotos는 서버에서 처리할 파라미터 이름입니다.
    });

    $.ajax({
        url: url,
        type: type,
        async: true,
        data: formData,  // FormData 객체 전송
        processData: false,  // jQuery가 데이터를 자동으로 변환하지 않도록 설정
        contentType: false,  // jQuery가 contentType을 자동으로 설정하도록 함
        success: function (result){
            $('#errorDiv').hide();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');  // 오류 메시지 텍스트를 p 태그에 삽입
            alert(result.msg)
            location.reload();
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}