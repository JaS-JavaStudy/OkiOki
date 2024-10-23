package payment;

import receipt.PrintReceipt;

import java.util.Scanner;

public class paymentCash implements PaymentMethod {
    @Override
    public void processPayment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("현금으로 결제가 완료되었습니다.");
        System.out.println("영수증을 출력하시겠습니까?");
        System.out.println("1. 네");
        System.out.println("2. 아니오");

        int choice = sc.nextInt();
        PrintReceipt printReceipt = null;

        switch (choice) {
            case 1:
                printReceipt = new PrintReceipt();
                printReceipt.print();
                break;
            case 2:
                System.out.println("영수증 출력을 선택하지 않았습니다.");
                break;
            default:
                System.out.println("잘못된 입력입니다. 1 또는 2를 선택하세요");
                break;
        }
        sc.close();
    }
}
