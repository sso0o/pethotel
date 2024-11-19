
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

    data = JSON.stringify({
        userId: userId,
        hotelName: hotelName,
        hotelType: hotelType,
        postcode: postcode,
        address: address,
        detailAddress: detailAddress,
        extraAddress: extraAddress,
        hotelPhone: hotelPhone,
        hotelInfo: hotelInfo
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
            location.reload();
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}