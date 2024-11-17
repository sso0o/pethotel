
// 코드 헤더 등록 및 수정 모달 오픈
function addHeadModal(codeId){
    // 처음 초기화
    document.getElementById('headForm').reset();

    if(codeId != "") {
        //수정모드
        $('#codeHead').attr("readonly", true);
        $("#saveBtn").hide();
        $("#modifyBtn").show();
        $("#deleteBtn").show();


    }else{
        //추가모드
        $('#codeHead').attr("readonly", false);
        $("#saveBtn").show();
        $("#modifyBtn").hide();
        $("#deleteBtn").hide();
    }
    let myModal = new bootstrap.Modal(document.getElementById('headModal'));
    myModal.show();
}

// 코드 헤더 저장 및 수정 삭제
function saveHead(type){
    let url;
    let data;
    let codeHead;
    let codeName;

    if (type === 'post'){
        codeHead = $('#codeHead').val();
        codeName = $('#codeName').val();

        codeHead == "" && (alert('input the code') || true);
        codeName == "" && (alert('input the name') || true);

        url = "/admin/code"
        data = JSON.stringify({
            codeHead : codeHead,
            codeDetail: '',
            codeName : codeName
        })
    }

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


// 디테일 코드 등록 및 수정 삭제 모달
function addDetailModal(codeId){

    if(codeId !== "") {
        //수정모드
        $('#codeHeadVal').attr("readonly", true);
        $("#saveBtn2").hide();
        $("#modifyBtn2").show();
        $("#deleteBtn2").show();


    }else{
        //추가모드
        $('#codeHeadVal').attr("readonly", true);
        $("#saveBtn2").show();
        $("#modifyBtn2").hide();
        $("#deleteBtn2").hide();
    }

    let codeHeadVal = $('#codeHeadVal').val();

    if(codeHeadVal === ""){
        alert("please select the code head")
        return;
    }else{
        let myModal = new bootstrap.Modal(document.getElementById('detailModal'));
        myModal.show();
    }

}


// 코드 디테일 저장 및 수정 삭제
function saveDetail(type){
    let url;
    let data;
    let codeHead;
    let codeDetail;
    let codeDetailName;

    if (type === 'post'){
        codeHead = $('#codeHeadVal').val();
        codeDetail = $('#codeDetail').val();
        codeDetailName = $('#codeDetailName').val();

        codeDetail == "" && (alert('input the code') || true);
        codeDetailName == "" && (alert('input the name') || true);

        url = "/admin/code"
        data = JSON.stringify({
            codeHead : codeHead,
            codeDetail : codeDetail,
            codeName : codeDetailName
        })
    }

    $.ajax({
        url: url,
        type: type,
        async: true,
        contentType: 'application/json',
        data: data,
        success: function (result){
            $('#errorDiv2').hide();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg2').text('');  // 오류 메시지 텍스트를 p 태그에 삽입
            alert(result.msg)
            location.reload();
        },
        error: function (request, status, error){
            let result = jQuery.parseJSON(request.responseText)
            $('#errorDiv2').show();  // 오류 메시지를 표시하는 div를 보이게
            $('#errorMsg2').text(result.msg);  // 오류 메시지에 삽입
        }
    })
}
