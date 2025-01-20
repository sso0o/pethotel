function addNewRoom(){
    let hotelId = $('#hotelId').val();
    let roomId = $('#roomId').val();
    let roomType = $('#roomType').val();
    let roomName = $('#roomName').val();
    let roomPrice = $('#roomPrice').val();
    let limitGuest = $('#limitGuest').val();
    let limitPet = $('#limitPet').val();
    let checkIn = $('#checkIn').val();
    let checkOut = $('#checkOut').val();
    let roomAmenities = $('#selectedRAMs').val();


    let bedType = $('#bedType').val();
    let viewType = $('#viewType').val();
    let pool = $('#pool').val();
    let roomCount = $('#roomCount').val();
    let bathCount = $('#bathCount').val();
    let balcony = $('#balcony').val();
    let kitchen = $('#kitchen').val();
    //let roomFeatures = [bedType, viewType, pool, roomCount, bathCount, balcony, kitchen].join(',');
    let data;
    data = JSON.stringify({
        hotelId : hotelId,
        roomType : roomType,
        roomName : roomName,
        roomPrice : roomPrice,
        limitGuest : limitGuest,
        limitPet : limitPet,
        checkIn : checkIn,
        checkOut : checkOut,
        roomAmenities : roomAmenities,
        //roomFeatures : roomFeatures//
        bedType : bedType,
        viewType : viewType,
        pool : pool,
        roomCount : roomCount,
        bathCount : bathCount,
        balcony : balcony,
        kitchen : kitchen
        // roomFeatures : roomFeatures//
    })
    $.ajax({
        url: '/manager/myroom',
        type: "post",
        async: false,
        contentType: 'application/json',
        data: data,
        success: function (result){
            $('#errorDiv').hide();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');  // 오류 메시지 텍스트를 p 태그에 삽입
            alert(result.msg);
            saveFile(result.room.roomId);
            location.href ='/manager/myhotelroom/'+hotelId;
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })




}

function saveFile(roomId){
    let formData = new FormData();
    // 파일 데이터를 FormData에 추가 (파일은 #hotelPhoto input을 사용)
    Array.from($('#roomPhotos')[0].files).forEach(function(file) {
        formData.append('roomPhotos', file);  // hotelPhotos는 서버에서 처리할 파라미터 이름입니다.
    });
    if (formData.get('roomPhotos') != null) {
        $.ajax({
            url: '/manager/myroomImg/'+BigInt(roomId),
            type: 'post',
            async: false,
            data: formData,  // FormData 객체 전송
            processData: false,  // jQuery가 데이터를 자동으로 변환하지 않도록 설정
            contentType: false,  // jQuery가 contentType을 자동으로 설정하도록 함
            success: function(result) {
            },
            error: function (request, status, error){
                let result = jQuery.parseJSON(request.responseText);
                alert(result.msg);  // 오류 메시지에 삽입
            }
        })

    }
}

function loadRoomDetail(roomId){
    $.ajax({
        url:'/manager/myroom/'+roomId,
        type: 'get',
        success:function (data){
            $('#roomId').val(data.room.roomId);
            $('#roomType').val(data.room.roomType);
            $('#roomName').val(data.room.roomName);
            $('#roomPrice').val(data.room.roomPrice);
            $('#limitGuest').val(data.room.limitGuest);
            $('#limitPet').val(data.room.limitPet);
            $('#checkIn').val(data.room.checkIn);
            $('#checkOut').val(data.room.checkOut);

            let roomAmenities = data.room.roomAmenities;

            let amenityChips = document.querySelectorAll('.chip');

            let selectedChipsInput = $('#selectedRAMs');
            let selectedChips = [];

            amenityChips.forEach(chip => {
                chip.classList.remove('active');
                let chipValue = chip.getAttribute('data-code');

                // 배열에서 해당 code가 있는지 확인
                if (roomAmenities.some(facility => facility.code === chipValue)) {
                    chip.classList.add('active');  // active 클래스 추가
                    selectedChips.push(chipValue);
                }
                selectedChipsInput.val(selectedChips.join(','));
            });

            $('#bedType').val(data.roomFeature.bedType);
            $('#viewType').val(data.roomFeature.viewType);
            $('#pool').val(data.roomFeature.pool);
            $('#roomCount').val(data.roomFeature.roomCount);
            $('#bathCount').val(data.roomFeature.bathCount);
            $('#balcony').val(data.roomFeature.balcony);
            $('#kitchen').val(data.roomFeature.kitchen);

            imgReLoad(data.room.roomPhotos);


        }
    })
}


function modifyRoom(roomId){
    let hotelId = $('#hotelId').val();
    let roomType = $('#roomType').val();
    let roomName = $('#roomName').val();
    let roomPrice = $('#roomPrice').val();
    let limitGuest = $('#limitGuest').val();
    let limitPet = $('#limitPet').val();
    let checkIn = $('#checkIn').val();
    let checkOut = $('#checkOut').val();
    let roomAmenities = $('#selectedRAMs').val();

    let bedType = $('#bedType').val();
    let viewType = $('#viewType').val();
    let pool = $('#pool').val();
    let roomCount = $('#roomCount').val();
    let bathCount = $('#bathCount').val();
    let balcony = $('#balcony').val();
    let kitchen = $('#kitchen').val();

    let data;
    data = JSON.stringify({
        hotelId : hotelId,
        roomType : roomType,
        roomName : roomName,
        roomPrice : roomPrice,
        limitGuest : limitGuest,
        limitPet : limitPet,
        checkIn : checkIn,
        checkOut : checkOut,
        roomAmenities : roomAmenities,
        //roomFeatures : roomFeatures//
        bedType : bedType,
        viewType : viewType,
        pool : pool,
        roomCount : roomCount,
        bathCount : bathCount,
        balcony : balcony,
        kitchen : kitchen
        // roomFeatures : roomFeatures//
    })


    let delImg = document.querySelectorAll('.btn-trash');
    let deletedImg = [];

    delImg.forEach(btn => {
        let imgValue = btn.closest('div').querySelector('img').getAttribute('data-value');  // 이미지의 'data-value' 값을 가져옴
        if (btn.classList.contains('deleted')) {
            deletedImg.push(imgValue);
        }
    })
    let delImgStr = deletedImg.join(',');


    $.ajax({
        url:'/manager/myroom/'+roomId,
        type: 'put',
        async: false,
        contentType: 'application/json',
        data: data,
        success: function (result){
            $('#errorDiv').hide();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');  // 오류 메시지 텍스트를 p 태그에 삽입
            alert(result.msg);
            saveFile(result.room.roomId);
            if(delImgStr.length > 0){
                delRoomImgRequest(delImgStr);
            }
            location.href ='/manager/myhotelroom/'+hotelId;
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}

function imgReLoad(roomPhotos){
    let divRoomPhotos = $('#divRoomPhotos');
    // 기존의 사진들 초기화 (새로 추가하기 전에 기존 내용을 지웁니다)
    divRoomPhotos.empty();

    // roomPhotos 배열을 순회하여 사진을 추가
    roomPhotos.forEach(photo => {
        console.log(photo);
        let photoDiv = `
        <div style="position: relative; display: inline-block; margin-right: 10px;" id="photo-${photo.rimgId}">
            <img src="http://localhost:8081/uploads/room/${photo.rimgFile}" alt="Room Image" 
            style="max-width: 100px; max-height: 100px;" class="room-image" data-value="${photo.rimgId}">
            <button type="button" class="btn btn-danger btn-trash"  onclick="delRoomImg('${photo.rimgId}')" 
            style="position: absolute; bottom: 5px; right: 5px; padding: 0.5rem; z-index: 10;">
            <i class="fas fa-trash"></i>
            </button>
        </div>
    `;

        // photoDiv를 divRoomPhotos에 추가
        divRoomPhotos.append(photoDiv);
    });

}
// 객실 이미지 삭제 요청
function delRoomImg(rimgId){
    let imageElement = document.getElementById(`photo-${rimgId}`);
    let img = imageElement.querySelector('.room-image');
    let btn = imageElement.querySelector('.btn-trash');

    // 이미지가 이미 삭제된 상태라면 복원
    if (img.classList.contains('deleted')) {
        img.classList.remove('deleted'); // 삭제 효과 제거
        btn.classList.remove('deleted'); // 삭제 버튼 효과 제거
    } else {
        // 이미지에 삭제 효과 추가
        img.classList.add('deleted');
        btn.classList.add('deleted');
    }

    // 서버에서 파일 삭제 요청 (예: 서버 API로 삭제)
    // if (confirm("정말 이 사진을 삭제?")){
    //     $.ajax({
    //         url: '/manager/myroomImg',  // 파일을 삭제할 서버 API 엔드포인트
    //         type: 'DELETE',  // DELETE 요청
    //         data: JSON.stringify({ rimgId: String(rimgId) }), // 본문에 이미지 URL 포함
    //         contentType: 'application/json', // JSON 형식으로 보내기
    //         success: function(result) {
    //             alert(result.msg);
    //             imgReLoad();
    //
    //         },
    //         error: function (request, status, error){
    //             let result = jQuery.parseJSON(request.responseText)
    //             alert(result.msg);
    //         }
    //     });
    // }

}

function delRoomImgRequest(delImgStr){
    $.ajax({
        url: '/manager/myroomImg',  // 파일을 삭제할 서버 API 엔드포인트
        type: 'DELETE',  // DELETE 요청
        data: JSON.stringify({ rimgId: String(delImgStr) }), // 본문에 이미지 URL 포함
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


function deleteRoom(roomId){
    $.ajax({
        url:'/manager/myroom/'+roomId,
        type: 'delete',
        async: false,
        contentType: 'application/json',
        success: function (result){
            $('#errorDiv').hide();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');  // 오류 메시지 텍스트를 p 태그에 삽입
            alert(result.msg);
            window.history.back();  // 이전 페이지로 돌아가기
            // location.href ='/manager/myhotelroom/'+hotelId;
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}