package payment;

import java.util.Scanner;

public class paymentMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 결제 금액 입력받기
        System.out.println("결제 금액을 입력하세요:");
        double paymentAmount = sc.nextDouble(); // 일단 테스트를 위해 입력값으로 설정

        PaymentMethod paymentMethod = null;
        int choice;

        do {
            System.out.println("결제 방법을 선택해 주세요 !");
            System.out.println("1. 카드");
            System.out.println("2. 삼성페이");
            System.out.println("3. 카카오페이");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    paymentMethod = new paymentCard(paymentAmount);  // 결제 금액 전달
                    break;
                case 2:
                    paymentMethod = new paymentSamsung(paymentAmount);
                    break;
                case 3:
                    paymentMethod = new paymentKakao(paymentAmount);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            }
        } while (paymentMethod == null); // 유효한 선택이 될 때까지 반복

        paymentMethod.processPayment();

        sc.close();
    }
}
