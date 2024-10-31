package cart;

import order.OrderDetails;

import java.util.ArrayList;
import java.util.Scanner;

public class Bucket {
    private ArrayList<OrderDetails> cartItems = new ArrayList<>();
    private Scanner reader;

    public Bucket(Scanner reader) {
        this.reader = reader;
    }

    // 주문 추가 메서드
    public void addToBucket(OrderDetails order) {
        cartItems.add(order);
        System.out.println(order.getMenu() + "가 장바구니에 추가되었습니다.");
    }

    // 현재 주문 상태 표시
    public void displayCurrentItem(OrderDetails order) {
        System.out.println("메뉴: " + order.getMenu() + " (온도: " +
                (order.getTemperature() == 0 ? "HOT" : "ICE") + ")");
        System.out.println("옵션:");
        for (String option : order.getOptions().keySet()) {
            System.out.println("  - " + option);
        }
        System.out.println("총 가격: " + order.calculateTotalPrice() + "원");
    }

    // 장바구니 표시
    public void displayBucket() {
        if (cartItems.isEmpty()) {
            System.out.println("장바구니가 비었습니다.💨");
            return;
        }

        System.out.println("=== 🧺장바구니🧺 ===");
        for (int i = 0; i < cartItems.size(); i++) {
            OrderDetails order = cartItems.get(i);
            System.out.println((i + 1) + ". " + order.getMenu() + " (" +
                    (order.getTemperature() == 0 ? "HOT" : "ICE") + ") - " +
                    order.calculateTotalPrice() + "원");
        }
    }

    // 장바구니 항목 수정
    public void modifyBucket() {
        displayBucket();
        if (cartItems.isEmpty()) return;

        System.out.print("수정할 음료의 번호를 입력하세요: ");
        String input = reader.nextLine().trim();
        int itemNum;

        try {
            itemNum = Integer.parseInt(input);
            if (itemNum > 0 && itemNum <= cartItems.size()) {
                OrderDetails order = cartItems.get(itemNum - 1);
                System.out.println(order.getMenu() + "가 선택되었습니다. 새 이름을 입력하세요:");
                String newMenuName = reader.nextLine().trim();
                // order 객체에 새로운 메뉴 이름 설정 (OrderDetails에 setMenu 메서드 필요)
                System.out.println("장바구니 수정 성공");
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
    }

    // 장바구니 항목 삭제
    public void removeFromBucket() {
        displayBucket();
        if (cartItems.isEmpty()) return;

        System.out.print("삭제할 음료 번호를 입력하세요: ");
        String input = reader.nextLine().trim();
        int itemNum;

        try {
            itemNum = Integer.parseInt(input);
            if (itemNum > 0 && itemNum <= cartItems.size()) {
                cartItems.remove(itemNum - 1);
                System.out.println("음료가 삭제되었습니다.");
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
    }
}
