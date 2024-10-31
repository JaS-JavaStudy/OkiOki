package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class temp_bucket01 {
    private ArrayList<Map<String, Object>> cart;
    private Scanner scanner;

    public temp_bucket01(){
        this.cart = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void add_order(Map<String, Object> orderInfo){
        Map<String, Object> newOrder = new HashMap<>(orderInfo);
        cart.add(newOrder);
    }

    public void display_bucket(){
        for (int i = 0; i < cart.size(); i++) {
            Map<String, Object> order = cart.get(i);
            System.out.println("-----");
            System.out.println((i + 1) + ". 메뉴: " + order.get("menu"));
            System.out.println("HOT/ICE: " + order.get("temperature"));
            System.out.println("선택된 옵션: " + order.get("options"));
            System.out.println("총 가격: " + order.get("price"));
            System.out.println("-----");
        }
    }

    public void delete_order() {
        display_bucket();
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.print("삭제할 음료 번호를 입력하세요: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // 개행 제거

        if (orderIndex >= 0 && orderIndex < cart.size()) {
            cart.remove(orderIndex);
            System.out.println("음료가 삭제되었습니다.");
        } else {
            System.out.println("잘못된 번호입니다.");
        }
    }

    public void modify_options() {
        display_bucket();
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.print("옵션을 수정할 음료 번호를 입력하세요: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // 개행 제거

        if (orderIndex >= 0 && orderIndex < cart.size()) {
            Map<String, Object> order = cart.get(orderIndex);
            Map<String, String> options = (Map<String, String>) order.get("options");

            System.out.print("수정할 옵션 이름을 입력하세요: ");
            String optionName = scanner.nextLine();

            if (options.containsKey(optionName)) {
                System.out.print("새 옵션 값을 입력하세요: ");
                String newOptionValue = scanner.nextLine();
                options.put(optionName, newOptionValue);

                System.out.println("옵션이 수정되었습니다.");
            } else {
                System.out.println("해당 옵션이 없습니다.");
            }
        } else {
            System.out.println("잘못된 번호입니다.");
        }
    }

    public void delete_options() {
        display_bucket();
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.print("옵션을 삭제할 음료 번호를 입력하세요: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // 개행 제거

        if (orderIndex >= 0 && orderIndex < cart.size()) {
            Map<String, Object> order = cart.get(orderIndex);
            Map<String, String> options = (Map<String, String>) order.get("options");

            System.out.print("삭제할 옵션 이름을 입력하세요: ");
            String optionName = scanner.nextLine();

            if (options.containsKey(optionName)) {
                options.remove(optionName);
                System.out.println("옵션이 삭제되었습니다.");
            } else {
                System.out.println("해당 옵션이 없습니다.");
            }
        } else {
            System.out.println("잘못된 번호입니다.");
        }
    }

    public ArrayList<Map<String, Object>> getCart() {
        return cart;
    }
}
