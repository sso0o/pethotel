
// 이미지 로딩 및 미리보기 추가
function imageLoader(file, sel_files) {
    sel_files.push(file); // 선택된 파일을 배열에 추가
    let reader = new FileReader(); // FileReader 객체 생성

    reader.onload = function(event) {
        let img = $('<img />', {
            src: event.target.result,  // 이미지 파일의 data URL
            style: 'width:100%; height:100%; z-index:none;', // 스타일 설정
        });

        // 이미지가 포함된 div 생성 후 미리보기 이미지 추가
        $('#preview_img').append(makeDiv(img, file, sel_files));
    };

    reader.readAsDataURL(file); // 파일을 data URL로 읽음
}

// 이미지와 삭제 버튼을 포함하는 div 생성
function makeDiv(img, file, sel_files) {
    let div = $('<div />', {
        style: 'display:inline-block; position:relative; width:50px; height:50px; margin:5px; border:1px solid #00f; z-index:1;'
    });

    let btn = $('<input />', {
        type: 'button',
        value: 'x',
        style: 'width:30px; height:30px; position:absolute; font-size:24px; right:0px; bottom:0px; z-index:999; background-color:rgba(255,255,255,0.1); color:#f00;',
        delFile: file.name,
    }).click(function() {
        let delFile = $(this).attr('delFile');

        // 선택된 파일을 배열에서 제거
        sel_files = sel_files.filter(function(f) {
            return f.name !== delFile;
        });

        // DataTransfer 객체로 파일 배열을 갱신
        let dt = new DataTransfer();
        sel_files.forEach(function(f) {
            dt.items.add(f);
        });

        // input에 업데이트된 파일 목록 할당
        $('#hotelPhotos')[0].files = dt.files;

        // 서버에서 파일 삭제 요청 (예: 서버 API로 삭제)
        $.ajax({
            url: '/manager/hotelImg/' + delFile,  // 파일을 삭제할 서버 API 엔드포인트
            type: 'DELETE',  // DELETE 요청
            success: function(response) {
            },
            error: function(xhr, status, error) {
            }
        });

        // 해당 div 삭제
        $(this).parent().remove();
    });

    // div에 이미지와 버튼 추가
    div.append(img);
    div.append(btn);

    return div;
}

// 수정화면 미리보기에서 삭제시 서버에서 삭제연동
function makeDelDiv(img, file, sel_files) {
    let div = $('<div />', {
        style: 'display:inline-block; position:relative; width:50px; height:50px; margin:5px; border:1px solid #00f; z-index:1;'
    });

    let btn = $('<input />', {
        type: 'button',
        value: 'x',
        style: 'width:30px; height:30px; position:absolute; font-size:24px; right:0px; bottom:0px; z-index:999; background-color:rgba(255,255,255,0.1); color:#f00;',
        delFile: file.name,
    }).click(function() {
        let delFile = $(this).attr('delFile');

        // 선택된 파일을 배열에서 제거
        sel_files = sel_files.filter(function(f) {
            return f.name !== delFile;
        });

        // DataTransfer 객체로 파일 배열을 갱신
        let dt = new DataTransfer();
        sel_files.forEach(function(f) {
            dt.items.add(f);
        });

        // input에 업데이트된 파일 목록 할당
        $('#hotelPhotos')[0].files = dt.files;

        // 해당 div 삭제
        $(this).parent().remove();
    });

    // div에 이미지와 버튼 추가
    div.append(img);
    div.append(btn);

    return div;
}