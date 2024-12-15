function drawTabMyBooking(data){
    let table = $('#tabMyBooking');
    let thead = table.find('thead');
    let tbody = table.find('tbody');

    // 기존의 테이블 내용을 지웁니다.
    tbody.empty();
    thead.empty();

    // 데이터가 존재하고 배열인 경우
    if (data && data.dates && Array.isArray(data.dates) && data.dates.length > 0) {
        let headerRow = `<tr><th>객실</th>`
        for (let i = 0; i < data.dates.length; i++) {
            let date = data.dates[i];
            headerRow += `<th>${date}</th>`;
        }
        headerRow += `</tr>`
        // 헤더 행을 테이블 헤더에 추가
        thead.append(headerRow);
    } else {
        console.error('데이터에서 헤더를 생성할 수 없습니다.');
    }

    if (data && data.paidBookings && Array.isArray(data.paidBookings) && data.paidBookings.length > 0) {
        for (let i = 0; i < data.paidBookings.length; i++) {
            let booking = data.paidBookings[i];
            let row = document.createElement('tr');
            for (let j = 0; j < booking.length; j++) {
                let cell = document.createElement('td');
                cell.textContent = booking[j];
                row.appendChild(cell);
            }
            tbody.append(row);
        }
    }




}

