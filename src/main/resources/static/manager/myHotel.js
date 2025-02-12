// 호텔 등록 및 수정 모달 오픈
function addHotelModal(hotelId) {

    if (hotelId === "add") {
        // 처음 초기화
        document.getElementById('hotelForm').reset();
        let facilityChips = document.querySelectorAll('.chip');
        facilityChips.forEach(chip => {
            chip.classList.remove('active');  // active 클래스 추가
        });
        // 추가모드
        $("#saveBtn").show();
        $("#modifyBtn").hide();
        $("#deleteBtn").hide();
    } else {
        // 수정 or 삭제모드
        $("#saveBtn").hide();
        $("#modifyBtn").show();
        $("#deleteBtn").show();

        $.ajax({
            url: '/manager/myhotel/' + BigInt(hotelId),
            type: 'get',
            success: function (data) {
                $('#hotelId').val(data.hotel.hotelId);
                $('#hotelName').val(data.hotel.hotelName);
                $('#location').val(data.hotel.location);
                $('#postcode').val(data.hotel.postcode);
                $('#address').val(data.hotel.address);
                $('#detailAddress').val(data.hotel.detailAddress);
                $('#extraAddress').val(data.hotel.extraAddress);
                $('#hotelPhone').val(data.hotel.hotelPhone);
                $('#hotelInfo').val(data.hotel.hotelInfo);

                let hotelFacilities = data.hotel.hotelFacilities;

                let facilityChips = document.querySelectorAll('.chip');

                let selectedChipsInput = $('#selectedChips');
                let selectedChips = [];

                facilityChips.forEach(chip => {
                    chip.classList.remove('active');
                    let chipValue = chip.getAttribute('data-code');

                    // 배열에서 해당 code가 있는지 확인
                    if (hotelFacilities.some(facility => facility.code === chipValue)) {
                        chip.classList.add('active');  // active 클래스 추가
                        selectedChips.push(chipValue);
                    }
                    selectedChipsInput.val(selectedChips.join(','));
                });
            },
        })
    }
    let myModal = new bootstrap.Modal(document.getElementById('hotelModal'));
    myModal.show();
}

function saveHotel(type) {
    let url;
    let data;
    let userId = $('#userId').val();
    let hotelId = $('#hotelId').val();
    let hotelName = $('#hotelName').val();
    let location = $('#location').val();
    let postcode = $('#postcode').val();
    let address = $('#address').val();
    let detailAddress = $('#detailAddress').val();
    let extraAddress = $('#extraAddress').val();
    let hotelPhone = $('#hotelPhone').val();
    let hotelInfo = $('#hotelInfo').val();

    let selectedChips = $('#selectedChips').val();



    if (type === 'post') {
        url = "/manager/myhotel"
    } else if (type === "put") {
        url = "/manager/myhotel/" + BigInt(hotelId);
    } else if (type === "delete") {
        url = "/manager/myhotel/" + BigInt(hotelId);
    }

    data = JSON.stringify({
        userId: userId,
        hotelName: hotelName,
        location: location,
        postcode: postcode,
        address: address,
        detailAddress: detailAddress,
        extraAddress: extraAddress,
        hotelPhone: hotelPhone,
        hotelInfo: hotelInfo,
        hotelFacilities: selectedChips.toString()
    })

    $.ajax({
        url: url,
        type: type,
        async: true,
        contentType: 'application/json',
        data: data,
        success: function (result) {
            $('#errorDiv').hide();
            $('#errorMsg').text('');
            alert(result.msg)
            window.location.reload();
        },
        error: function (request, status, error) {
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg').text('');
            $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}

function deleteHotel() {
    let hotelId = $('#hotelId').val();
    let url = "/manager/myhotel/" + BigInt(hotelId);

    if (confirm("Are you sure?")) {
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function (result) {
                $('#errorDiv').hide();
                $('#errorMsg').text('');
                alert(result.msg)
                window.location.reload();
            },
            error: function (request, status, error) {
                let result = jQuery.parseJSON(request.responseText)
                $('#errorDiv').show();  // 오류 메시지를 표시하는 div를 보이게
                $('#errorMsg').text('');
                $('#errorMsg').text(result.msg);  // 오류 메시지에 삽입
            }
        })
    }
}


// 이미지 테이블에 이미지 렌더링
function imgRender(data) {
    let imgData = `
        <div style="display: flex; align-items: end">
            <img src="${data.himgUrl}" alt="Hotel Image" style="max-width: 100px; max-height: 100px;">
            <button type="button" class="btn btn-danger" style="height: fit-content;" onclick="delHotelImg('${data.himgId}')">
                <i class="fas fa-trash"></i>
            </button>
        </div>
    `;

    return imgData;
}

// 호텔 이미지 삭제 요청
function delHotelImg(himgId) {
    // 서버에서 파일 삭제 요청 (예: 서버 API로 삭제)
    if (confirm("정말 이 사진을 삭제?")) {
        $.ajax({
            url: '/manager/myhotelImg',  // 파일을 삭제할 서버 API 엔드포인트
            type: 'DELETE',  // DELETE 요청
            data: JSON.stringify({himgId: String(himgId)}), // 본문에 이미지 URL 포함
            contentType: 'application/json', // JSON 형식으로 보내기
            success: function (result) {
                alert(result.msg);
                imgReLoad();

            },
            error: function (request, status, error) {
                let result = jQuery.parseJSON(request.responseText)
                alert(result.msg);
            }
        });
    }

}

function imgReLoad() {
    let hotelId = $('#hotelId').val();
    // 이미지 로드
    $.ajax({
        url: '/manager/myhotel/' + hotelId,
        type: 'get',
        success: function (data) {
            $('#tabHotelImg').DataTable().clear().rows.add(data.hotel.hotelPhotos).draw();
        }
    })

}
