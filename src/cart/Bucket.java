package cart;

import java.util.*;

import order.OrderDetails;

import java.util.Map;

public class Bucket {
    private ArrayList<Map<String, Object>> cart;
    private Scanner scanner;

    public Bucket() {
        this.cart = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("===== 현재 장바구니 =====");
        display_bucket();
    }

    public void add_order(Map<String, Object> orderInfo) {
        // 디버깅을 위한 출력
//        System.out.println("Debug - OrderInfo content:");
//        for (Map.Entry<String, Object> entry : orderInfo.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue() + " (Type: " + entry.getValue().getClass().getName() + ")");
//        }

        Map<String, Object> newOrder = new HashMap<>();

        newOrder.put("menu", String.valueOf(orderInfo.get("menu")));
        newOrder.put("temperature", String.valueOf(orderInfo.get("temperature")));
        newOrder.put("price", ((Number) orderInfo.get("price")).doubleValue());

        // options 처리
        Map<String, Object> originalOptions = (Map<String, Object>) orderInfo.get("options");
        Map<String, Object> newOptions = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalOptions.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Number) {
                newOptions.put(entry.getKey(), ((Number) value).doubleValue());
            } else {
                newOptions.put(entry.getKey(), String.valueOf(value));
            }
        }
        newOrder.put("options", newOptions);

        cart.add(newOrder);
    }

    public void display_bucket() {
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

    public void delete_options() {
        display_bucket();
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.print("옵션을 삭제할 음료 번호를 입력하세요: ");
        int orderIndex = -1;

        while (true) {
            try {
                orderIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // 개행 제거
                break; // exit loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine(); // clear invalid input
            }
        }

        if (orderIndex >= 0 && orderIndex < cart.size()) {
            Map<String, Object> order = cart.get(orderIndex);
            Map<String, Object> options = (Map<String, Object>) order.get("options");

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

    public List<OrderDetails> getCart() {
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (Map<String, Object> order : cart) {
            try {
                // 기본 정보 가져오기
                String menu = String.valueOf(order.get("menu"));
                double price = ((Number) order.get("price")).doubleValue();

                // temperature 처리
                String tempString = String.valueOf(order.get("temperature"));
                int temperature = "HOT".equalsIgnoreCase(tempString) ? 0 : 1;

                // options 처리
                Map<String, Object> originalOptions = (Map<String, Object>) order.get("options");
                Map<String, Object> convertedOptions = new HashMap<>();

                for (Map.Entry<String, Object> entry : originalOptions.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof Integer) {
                        convertedOptions.put(entry.getKey(), value);
                    } else {
                        convertedOptions.put(entry.getKey(), String.valueOf(value));
                    }
                }

                OrderDetails orderDetails = new OrderDetails(menu, price, temperature, convertedOptions);
                orderDetailsList.add(orderDetails);
            } catch (Exception e) {
                System.out.println("주문 처리 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return orderDetailsList;
    }
}