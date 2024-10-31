package cart;

import order.OrderDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bucket {
    private ArrayList<Map<String, Object>> cart;
    private Scanner scanner;

    public Bucket() {
        this.cart = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void add_order(Map<String, Object> orderInfo) {
        // 디버깅을 위한 출력
        System.out.println("Debug - OrderInfo content:");
        for (Map.Entry<String, Object> entry : orderInfo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " (Type: " + entry.getValue().getClass().getName() + ")");
        }

        Map<String, Object> newOrder = new HashMap<>();

        // 각 필드를 명시적으로 변환하여 저장
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
            Map<String, Object> options = (Map<String, Object>) order.get("options");

            System.out.print("수정할 옵션 이름을 입력하세요: ");
            String optionName = scanner.nextLine();

            if (options.containsKey(optionName)) {
                System.out.print("새 옵션 값을 입력하세요: ");
                String newOptionValue = scanner.nextLine();

                try {
                    // 숫자인 경우 Double로 저장
                    double numericValue = Double.parseDouble(newOptionValue);
                    options.put(optionName, numericValue);
                } catch (NumberFormatException e) {
                    // 숫자가 아닌 경우 문자열로 저장
                    options.put(optionName, newOptionValue);
                }

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

                // options의 각 값을 적절한 타입으로 변환
                for (Map.Entry<String, Object> entry : originalOptions.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof Integer) {
                        // 정수값은 그대로 유지
                        convertedOptions.put(entry.getKey(), value);
                    } else {
                        // 다른 값들은 String으로 변환
                        convertedOptions.put(entry.getKey(), String.valueOf(value));
                    }
                }

                OrderDetails orderDetails = new OrderDetails(menu, price, temperature, convertedOptions);
                orderDetailsList.add(orderDetails);
            } catch (Exception e) {
                System.out.println("주문 처리 중 오류 발생: " + e.getMessage());
                e.printStackTrace(); // 스택 트레이스 출력 추가
            }
        }

        return orderDetailsList;
    }
}