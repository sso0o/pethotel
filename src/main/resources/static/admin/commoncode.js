// 코드 헤더 등록 및 수정 모달 오픈
function addHeadModal(codeId){
    $('#errorDiv').hide();  // 오류 메시지를 표시하는 div를 보이게
    $('#errorMsg').text('');  // 오류 메시지 텍스트를 p 태그에 삽입

    if(codeId === "add") {
        // 처음 초기화
        document.getElementById('headForm').reset();
        // 추가모드
        $('#codeHead').attr("readonly", false);
        $("#saveBtn").show();
        $("#modifyBtn").hide();
        $("#deleteBtn").hide();
    }else{
        // 수정 or 삭제모드
        $('#codeHead').attr("readonly", true);
        $("#saveBtn").hide();
        $("#modifyBtn").show();
        $("#deleteBtn").show();

        $.ajax({
            url:'/admin/code/'+BigInt(codeId),
            type:'get',
            success: function (data){
                $('#codeId').val(data.id);
                $('#codeHead').val(data.codeHead);
                $('#codeName').val(data.codeName);
            }
        })
    }
    let myModal = new bootstrap.Modal(document.getElementById('headModal'));
    myModal.show();
}

// 코드 헤더 저장 및 수정 삭제
function saveHead(type){
    let url;
    let data;
    let codeId;
    let codeHead;
    let codeName;
    let codeUse = "Y"

    codeId = $('#codeId').val();
    codeHead = $('#codeHead').val();
    codeName = $('#codeName').val();



    if (type == 'post'){
        codeHead == "" && (alert('input the code') || true);
        codeName == "" && (alert('input the name') || true);
        url = "/admin/code"
    } else if (type === "put"){
        url = "/admin/code/"+BigInt(codeId);
    } else if (type === "del"){
        codeUse = "N"
        url = "/admin/code/"+BigInt(codeId);
        type = "put"
    }

    data = JSON.stringify({
        codeHead : codeHead,
        codeDetail: '',
        codeName : codeName,
        codeUse : codeUse
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


// 디테일 코드 등록 및 수정 삭제 모달
function addDetailModal(codeId){

    if(codeId === "add") {
        // 추가모드
        $('#codeHeadVal').attr("readonly", true);
        $("#saveBtn2").show();
        $("#modifyBtn2").hide();
        $("#deleteBtn2").hide();

        let codeHeadVal = $('#codeHeadVal').val();
        if(codeHeadVal === ""){
            alert("please select the code head")
            return;
        }
    }else{
        // 수정모드
        $('#codeHeadVal').attr("readonly", true);
        $("#saveBtn2").hide();
        $("#modifyBtn2").show();
        $("#deleteBtn2").show();

        $.ajax({
            url:'/admin/code/'+BigInt(codeId),
            type:'get',
            success: function (data){
                $('#codeId2').val(data.id);
                $('#codeHeadVal').val(data.codeHead);
                $('#codeDetail').val(data.codeDetail);
                $('#codeDetailName').val(data.codeName);
            }
        })
    }

    let myModal = new bootstrap.Modal(document.getElementById('detailModal'));
    myModal.show();

}


// 코드 디테일 저장 및 수정 삭제
function saveDetail(type){
    let url;
    let data;
    let codeId;
    let codeHead;
    let codeDetail;
    let codeName;
    let codeUse = "Y"

    codeId = $('#codeId2').val();
    codeHead = $('#codeHeadVal').val();
    codeDetail = $('#codeDetail').val();
    codeName = $('#codeDetailName').val();

    if (type == 'post'){
        codeDetail == "" && (alert('input the detail') || true);
        codeName == "" && (alert('input the name') || true);
        url = "/admin/code"
    } else if (type === "put"){
        url = "/admin/code/"+BigInt(codeId);
    } else if (type === "del"){
        codeUse = "N"
        url = "/admin/code/"+BigInt(codeId);
        type = "put"
    }

    data = JSON.stringify({
        codeHead : codeHead,
        codeDetail: codeDetail,
        codeName : codeName,
        codeUse : codeUse
    })

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
