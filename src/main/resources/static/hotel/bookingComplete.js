function drawPaymentDetail(){
    let paymentDetail = $('#paymentDetail');
    let item = `
    <h3>결제 대기 내역</h3>
    <p>결제 방법 : ${searchData.paymentMethod}</p>
    <p>결제 ���� : ${ (roomPrice * searchData.dateDiff * searchData.room).toLocaleString() } 원</p>
    `;

    paymentDetail.append(item);
}