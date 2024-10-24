package receipt;

public class PrintReceipt {

    public PrintReceipt() {
//        this.orderDetails = orderDetails; // 장바구니 목록 출력
    }

    public void print() {
        // 주문 내역 출력하는 곳
        System.out.println("========영수증========");
//        System.out.println("주문 내역" + orderDetails);
        System.out.println("=====================");
        System.out.println("영수증이 발급되었습니다.");
        System.out.println("영수증을 챙겨 주세요 !");
    }
}
