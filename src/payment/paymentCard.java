package payment;

import java.util.List;
import java.util.Scanner;
import order.OrderDetails;
import receipt.PrintReceipt;

public class paymentCard implements PaymentMethod {
    private double paymentAmount;
    private List<OrderDetails> orderDetailsList;

    public paymentCard(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
        this.paymentAmount = orderDetailsList.stream().mapToDouble(OrderDetails::calculateTotalPrice).sum();
    }

    @Override
    public void processPayment() {
        Scanner sc = new Scanner(System.in);

        int pointChoice;
        double points = 0;
        do {
            System.out.println();
            System.out.println("포인트를 적립하시겠습니까?");
            System.out.println("1. 네");
            System.out.println("2. 아니오");

            pointChoice = sc.nextInt();

            if (pointChoice == 1) {
                points = paymentAmount * 0.05;
                System.out.println();
                System.out.printf("포인트가 %.0f 포인트 적립되었습니다.\n", points);
            } else if (pointChoice == 2) {
                System.out.println("포인트 적립을 선택하지 않았습니다.");
            } else {
                System.out.println("잘못된 입력입니다. 1 또는 2를 선택하세요");
            }
        } while (pointChoice != 1 && pointChoice != 2);

        // 결제 완료 메시지
        System.out.println();
        System.out.println("카드로 결제가 완료되었습니다.");

        int receiptChoice;
        do {
            System.out.println();
            System.out.println("영수증을 출력하시겠습니까?");
            System.out.println("1. 네");
            System.out.println("2. 아니오");

            receiptChoice = sc.nextInt();

            if (receiptChoice == 1) {
                PrintReceipt printReceipt = new PrintReceipt(orderDetailsList); // 변경된 부분
                printReceipt.print();
            } else if (receiptChoice == 2) {
                System.out.println();
                System.out.println("영수증 출력을 선택하지 않았습니다.");
            } else {
                System.out.println();
                System.out.println("잘못된 입력입니다. 1 또는 2를 선택하세요");
            }
        } while (receiptChoice != 1 && receiptChoice != 2);

        sc.close();
    }
}
