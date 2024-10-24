package payment;

import java.util.Scanner;
import receipt.PrintReceipt;

public class paymentSamsung implements PaymentMethod {
    // 결제된 금액을 인스턴스 변수로 가정
    private double paymentAmount;

    // 생성자를 통해 결제 금액을 초기화
    public paymentSamsung (double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public void processPayment() {
        Scanner sc = new Scanner(System.in);

        int pointChoice;

        do {
            // 포인트 적립 여부 확인
            System.out.println("포인트를 적립하시겠습니까?");
            System.out.println("1. 네");
            System.out.println("2. 아니오");
            pointChoice = sc.nextInt();

            if (pointChoice == 1) {
                // 포인트 적립 (5% 적립)
                double points = paymentAmount * 0.05;
                System.out.printf("포인트가 %.0f 포인트 적립되었습니다.\n", points);
            } else if (pointChoice == 2) {
                System.out.println("포인트 적립을 선택하지 않았습니다.");
            } else {
                System.out.println("잘못된 입력입니다. 1 또는 2를 선택하세요");
            }
        } while (pointChoice != 1 && pointChoice != 2);

        int receiptChoice;

        do {
            // 결제 완료 메시지
            System.out.println("삼성페이로 결제가 완료되었습니다.");

            // 영수증 출력 여부 확인
            System.out.println("영수증을 출력하시겠습니까?");
            System.out.println("1. 네");
            System.out.println("2. 아니오");

            PrintReceipt printReceipt = null;
            receiptChoice = sc.nextInt();

            switch (receiptChoice) {
                case 1:
                    printReceipt = new PrintReceipt();
                    printReceipt.print();
                    break;
                case 2:
                    System.out.println("영수증 출력을 선택하지 않았습니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 1 또는 2를 선택하세요");
            }
        } while (receiptChoice != 1 && receiptChoice != 2);
        sc.close();
    }
}
