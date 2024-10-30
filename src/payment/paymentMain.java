package payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import order.OrderDetails;

public class paymentMain {
    public static void main(String[] args) {
        // 여러 주문 정보를 저장할 리스트
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        // Map으로 들어오는 음료 정보들 예시
        Map<String, Object> options1 = new HashMap<>();
        options1.put("샷 추가", 500.0);
        orderDetailsList.add(new OrderDetails("아메리카노", 3000, 1, options1));

        Map<String, Object> options2 = new HashMap<>();
        options2.put("바닐라 시럽 추가", 300.0);
        orderDetailsList.add(new OrderDetails("카페 라떼", 3500, 1, options2));

        // 여러 음료의 총 금액 계산
        double totalOrderAmount = orderDetailsList.stream().mapToDouble(OrderDetails::calculateTotalPrice).sum();

        // 총 주문 금액 출력
        System.out.println("총 주문 금액: " + totalOrderAmount);

        PaymentMethod paymentMethod = null;
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("결제 방법을 선택해 주세요!");
            System.out.println("1. 카드");
            System.out.println("2. 삼성페이");
            System.out.println("3. 카카오페이");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    paymentMethod = new paymentCard(orderDetailsList); // 모든 주문 정보 전달
                    break;
                case 2:
                    paymentMethod = new paymentSamsung(orderDetailsList);
                    break;
                case 3:
                    paymentMethod = new paymentKakao(orderDetailsList);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            }
        } while (paymentMethod == null); // 유효한 선택이 될 때까지 반복

        paymentMethod.processPayment();
        sc.close();
    }
}
