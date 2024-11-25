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


// 이미지 테이블에 이미지 렌더링
function imgRender(data){
    let imgData = `
        <div style="display: flex; align-items: end">
            <img src="${data.rimgUrl}" alt="Room Image" style="max-width: 100px; max-height: 100px;">
            <button type="button" class="btn btn-danger" style="height: fit-content;" onclick="delRoomImg('${data.rimgId}')">
                <i class="fas fa-trash"></i>
            </button>
        </div>
    `;

    return imgData;
}

// 객실 이미지 삭제 요청
function delRoomImg(rimgId){
    // 서버에서 파일 삭제 요청 (예: 서버 API로 삭제)
    if (confirm("정말 이 사진을 삭제?")){
        $.ajax({
            url: '/manager/myroomImg',  // 파일을 삭제할 서버 API 엔드포인트
            type: 'DELETE',  // DELETE 요청
            data: JSON.stringify({ rimgId: String(rimgId) }), // 본문에 이미지 URL 포함
            contentType: 'application/json', // JSON 형식으로 보내기
            success: function(result) {
                alert(result.msg);
                imgReLoad();

            },
            error: function (request, status, error){
                let result = jQuery.parseJSON(request.responseText)
                alert(result.msg);
            }
        });
    }

}

function imgReLoad(){
    let roomId = $('#roomId').val();
    // 이미지 로드
    $.ajax({
        url:'/manager/myroom/'+roomId,
        type: 'get',
        success:function (data){
            $('#tabRoomImg').DataTable().clear().rows.add(data.room.roomPhotos).draw();
            $('#roomPhotos').val(''); // 파일 input 초기화
        }
    })

}
