package receipt;

import order.OrderDetails;
import java.util.List;
import java.util.Map;

public class PrintReceipt {
    private List<OrderDetails> orderDetailsList; // 여러 주문 정보를 받을 리스트

    public PrintReceipt(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public void print() {
        System.out.println();
        System.out.println("========영수증========");
        double totalOrderPrice = 0; // 총 결제 금액 초기화

        for (OrderDetails orderDetails : orderDetailsList) {
            System.out.println("메뉴: " + orderDetails.getMenu());
            System.out.println("가격: " + orderDetails.getBasePrice()); // 기본 가격
            System.out.println("온도: " + (orderDetails.getTemperature() == 0 ? "Hot" : "Ice"));

            // 옵션 출력
            if (!orderDetails.getOptions().isEmpty()) {
                System.out.println("옵션:");
                for (Map.Entry<String, Object> option : orderDetails.getOptions().entrySet()) {
                    System.out.println(" - " + option.getKey() + ": " + option.getValue());
                }
            }
            double totalPrice = orderDetails.calculateTotalPrice(); // 총 가격 계산
            System.out.println("총 결제 금액: " + totalPrice); // 옵션 포함한 총 금액
            totalOrderPrice += totalPrice; // 전체 결제 금액에 추가
            System.out.println("---------------------");
        }
        System.out.println("전체 결제 금액: " + totalOrderPrice); // 모든 음료의 총 결제 금액 출력
        System.out.println("=====================");
        System.out.println("영수증이 발급되었습니다.");
        System.out.println("영수증을 챙겨 주세요!");
    }
}
