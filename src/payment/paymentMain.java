package payment;

import java.util.Scanner;

public class paymentMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("결제 방법을 선택해 주세요 !");
        System.out.println("1. 현금");
        System.out.println("2. 카드");
        System.out.println("3. 카카오 페이");

        int choice = sc.nextInt();
        PaymentMethod paymentMethod = null;

        switch (choice) {
            case 1:
                paymentMethod = new paymentCash();
                break;
            case 2:
                paymentMethod = new paymentCard();
                break;
            case 3:
                paymentMethod = new paymentKakao();
                break;
            default:
                System.out.println("잘못된 선택입니다. 다시 선택하세요.");
                return;
        }
        paymentMethod.processPayment();

        sc.close();
    }
}
