package cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bucket {
    private ArrayList<String> cartItems = new ArrayList<>();
    private BufferedReader reader;

    public Bucket(BufferedReader reader) {
        this.reader = reader;
    }

    // 음료 추가 메서드
    public void addToBucket(String item, Options options) {
        StringBuilder cartEntry = new StringBuilder(item);
        boolean[] selectedOptions = options.getSelectedOptionStatus();
        String[] optionNames = options.getSelectedOptions();

        cartEntry.append(" (");
        for (int i = 0; i < selectedOptions.length; i++) {
            if (selectedOptions[i]) {
                cartEntry.append("+").append(optionNames[i]).append(" ");
            }
        }
        cartEntry.append(")");

        cartItems.add(cartEntry.toString());
        System.out.println(item + " " + cartEntry.toString() + "가 장바구니에 추가되었습니다.");
    }

    // 현재 음료 상태 표시
    public void displayCurrentItem(String item, Options options) {
        StringBuilder currentItem = new StringBuilder(item);
        boolean[] selectedOptions = options.getSelectedOptionStatus();
        String[] optionNames = options.getSelectedOptions();

        currentItem.append(" (");
        for (int i = 0; i < selectedOptions.length; i++) {
            if (selectedOptions[i]) {
                currentItem.append("+").append(optionNames[i]).append(" ");
            }
        }
        currentItem.append(")");

        System.out.println("음료와 옵션: " + currentItem.toString());
    }

    // 장바구니 표시
    public void displayBucket() {
        if (cartItems.isEmpty()) {
            System.out.println("장바구니가 비었습니다.💨");
            return;
        }

        System.out.println("=== 🧺장바구니🧺 ===");
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i));
        }
    }

    // 장바구니 항목 수정
    public void modifyBucket() throws IOException {
        displayBucket();

        if (cartItems.isEmpty()) return;

        System.out.print("수정할 음료의 번호를 입력하세요: ");
        String input = reader.readLine().trim();
        int itemNum;

        try {
            itemNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (itemNum > 0 && itemNum <= cartItems.size()) {
            System.out.print("수정할 음료 이름을 입력하세요: ");
            String newItem = reader.readLine().trim();
            cartItems.set(itemNum - 1, newItem);
            System.out.println("장바구니 수정 성공");
        } else {
            System.out.println("잘못된 번호입니다.");
        }
    }

    // 장바구니 항목 삭제
    public void removeFromBucket() throws IOException {
        displayBucket();

        if (cartItems.isEmpty()) return;

        System.out.print("삭제할 음료 번호를 입력하세요: ");
        String input = reader.readLine().trim();
        int itemNum;

        try {
            itemNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (itemNum > 0 && itemNum <= cartItems.size()) {
            cartItems.remove(itemNum - 1);
            System.out.println("음료가 삭제되었습니다.");
        } else {
            System.out.println("잘못된 번호입니다.");
        }
    }
}
