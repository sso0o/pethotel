function drawTabMyBooking(data){
    // 데이터가 존재하고 배열인 경우
    if (data && data.dates && Array.isArray(data.dates) && data.dates.length > 0) {

        let tabMyBooking = $('#tabMyBooking').DataTable();


        let columns = [
            { data: 'roomName' }
        ];

        data.dates.forEach(function (date, index) {
            columns.push({ data: 'dates[' + index + ']', title: date });
        });

        tabMyBooking.clear().destroy();
        tabMyBooking = $('#tabMyBooking').DataTable({
            fixedColumns: {
                start: 1
            },
            paging: false,
            scrollCollapse: false,
            scrollX: true,
            searching: false,
            select: {
                style: 'single'
            },
            columns: columns
        })

        data.paidBookings.roomData.forEach(function(room) {
            let rowData = { roomName: room.roomName };

            room.dates.forEach(function(status, index) {
                rowData['dates[' + index + ']'] = status.trim();  // 날짜에 맞는 상태 추가
            });

            tabMyBooking.row.add(rowData).draw();
        });
    } else {
        console.error('데이터에서 헤더를 생성할 수 없습니다.');
    }

}

