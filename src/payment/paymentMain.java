package payment;

import order.OrderDetails;
import java.util.List;
import java.util.Scanner;

public class paymentMain {
    public static void processPayment(List<OrderDetails> orderDetailsList) {
        double totalOrderAmount = orderDetailsList.stream().mapToDouble(OrderDetails::calculateTotalPrice).sum();
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
                    paymentMethod = new paymentCard(orderDetailsList);
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
        } while (paymentMethod == null);

        paymentMethod.processPayment();
        sc.close();
    }
}
