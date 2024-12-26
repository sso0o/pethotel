function addNewRoom(){
    let hotelId = $('#hotelId').val();
    let roomId = $('#roomId').val();
    let roomType = $('#roomType').val();
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

            let roomFeatures = data.room.roomFeatures;
            let features = roomFeatures.split(',');
            console.log(features[0]);
            $('#bedType').val(features[0]);
            $('#viewType').val(features[1]);
            $('#pool').val(features[2]);
            $('#roomCount').val(features[3]);
            $('#bathCount').val(features[4]);
            $('#balcony').val(features[5]);
            $('#kitchen').val(features[6]);
        }
    })
}


function modifyRoom(roomId){


}