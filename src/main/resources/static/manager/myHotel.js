
// 호텔 등록 및 수정 모달 오픈
function addHotelModal(codeId){

    if(codeId === "add") {
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


    if (type == 'post'){
        url = "/manager/myhotel"
    } else if (type === "put"){

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