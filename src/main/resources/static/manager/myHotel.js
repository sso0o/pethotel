
// 호텔 등록 및 수정 모달 오픈
function addHotelModal(hotelId){

    if(hotelId === "add") {
        // 처음 초기화
        document.getElementById('hotelForm').reset();
        // 추가모드
        $("#saveBtn").show();
        $("#modifyBtn").hide();
        $("#deleteBtn").hide();
    }else{
        // 수정 or 삭제모드
        $("#saveBtn").hide();
        $("#modifyBtn").show();
        $("#deleteBtn").show();

        $.ajax({
            url:'/manager/myhotel/'+BigInt(hotelId),
            type:'get',
            success: function (data){

                $('#hotelId').val(data.hotel.hotelId);
                $('#hotelName').val(data.hotel.hotelName);
                $('#hotelType').val(data.hotel.hotelType);
                $('#postcode').val(data.hotel.postcode);
                $('#address').val(data.hotel.address);
                $('#detailAddress').val(data.hotel.detailAddress);
                $('#extraAddress').val(data.hotel.extraAddress);
                $('#hotelPhone').val(data.hotel.hotelPhone);
                $('#hotelInfo').val(data.hotel.hotelInfo);

                // 기존 이미지 미리보기 처리
                if (data.hotel.hotelPhotos && data.hotel.hotelPhotos.length > 0) {
                    $('#preview_img').empty(); // 기존 미리보기 이미지 초기화
                    data.hotel.hotelPhotos.forEach(function(photo) {
                        let img = $('<img />', { src: photo.himgUrl, style: 'width:100%; height:100%;' });
                        $('#preview_img').append(makeDelDiv(img, photo, []));
                    });
                }

                $('#errorDiv').hide();
                $('#errorMsg').text('');

            },
        })
    }
    let myModal = new bootstrap.Modal(document.getElementById('hotelModal'));
    myModal.show();
}

function saveHotel(type){

    let url;
    let data;
    let userId = $('#userId').val();
    let hotelId = $('#hotelId').val();
    let hotelName = $('#hotelName').val();
    let hotelType = $('#hotelType').val();
    let postcode = $('#postcode').val();
    let address = $('#address').val();
    let detailAddress = $('#detailAddress').val();
    let extraAddress = $('#extraAddress').val();
    let hotelPhone = $('#hotelPhone').val();
    let hotelInfo = $('#hotelInfo').val();


    if (type === 'post'){
        url = "/manager/myhotel"
    } else if (type === "put"){
        url = "/manager/myhotel/"+BigInt(hotelId);
    } else if (type === "del"){

    }

    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('hotelName', hotelName);
    formData.append('hotelType', hotelType);
    formData.append('postcode', postcode);
    formData.append('address', address);
    formData.append('detailAddress', detailAddress);
    formData.append('extraAddress', extraAddress);
    formData.append('hotelPhone', hotelPhone);
    formData.append('hotelInfo', hotelInfo);

    // 파일 데이터를 FormData에 추가 (파일은 #hotelPhoto input을 사용)
    Array.from($('#hotelPhotos')[0].files).forEach(function(file) {
        formData.append('hotelPhotos', file);  // hotelPhotos는 서버에서 처리할 파라미터 이름입니다.
    });

    $.ajax({
        url: url,
        type: type,
        async: true,
        data: formData,  // FormData 객체 전송
        processData: false,  // jQuery가 데이터를 자동으로 변환하지 않도록 설정
        contentType: false,  // jQuery가 contentType을 자동으로 설정하도록 함
        success: function (result){
            $('#errorDiv').hide();
            $('#errorMsg').text('');
            alert(result.msg)
            location.reload();
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');
            $('#errorMsg').text(result.error);  // 오류 메시지에 삽입
        }
    })
}