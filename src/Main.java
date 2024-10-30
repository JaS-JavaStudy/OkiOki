import menu.Menu;
import menu.Option;
import menu.Temperature;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 장바구니에 넘겨줄 orderInfo
        Map<String, Object> orderInfo = new HashMap<>();
        System.out.println("=== 오키오키 커피 주문 키오스크 ===");

        while (true) {
            // 1. 메뉴 선택
            Menu.displayMenu();
            int menuChoice = -1;
            Menu selectedMenu = null;

            // 유효한 메뉴 선택이 될 때까지 반복
            while (selectedMenu == null) {
                System.out.println("메뉴를 선택해 주세요:");
                try {
                    menuChoice = Integer.parseInt(scanner.nextLine());
                    selectedMenu = Menu.getMenu(menuChoice);
                    if (selectedMenu == null) {
                        System.out.println("잘못된 메뉴 선택입니다. 다시 선택해 주세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해야 합니다. 다시 선택해 주세요.");
                }
            }

            // 기본 메뉴 정보 저장
            orderInfo.put("menu", selectedMenu.getMenuName());

            // 2. 온도 선택
            int tempChoice;

            // 아이스 ONLY 메뉴 선택 시
            if (selectedMenu.isIceOnly()) {
                tempChoice = 1;
                System.out.println(selectedMenu.getMenuName() + "는 ICE ONLY입니다.");
            } else {
                // 유효한 온도 선택이 될 때까지 반복
                while (true) {
                    Temperature.displayTemperature();
                    System.out.println("선택해 주세요 (0: HOT, 1: ICE):");
                    try {
                        tempChoice = Integer.parseInt(scanner.nextLine());
                        if (Temperature.isValidTemperature(tempChoice)) {
                            System.out.println(Temperature.getTemperatureName(tempChoice) + "으로 선택되었습니다.");
                            break;
                        } else {
                            System.out.println("0 또는 1 중에 선택해 주세요.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해야 합니다. 다시 선택해 주세요.");
                    }
                }
            }

            // 온도 정보 저장 (0이면 hot, 1이면 ice)
            orderInfo.put("temperature", tempChoice);

            // 3. 옵션 선택
            Map<String, Integer> selectedOptions = new HashMap<>();
            int optionChoice = -1;
            while (optionChoice != 0) {
                Option.displayOptions();  // 옵션 목록 출력
                System.out.println("옵션을 선택하세요. (종료: 0)");
                try {
                    optionChoice = Integer.parseInt(scanner.nextLine());
                    if (optionChoice != 0) {
                        Option selectedOption = Option.getOption(optionChoice);
                        if (selectedOption != null) {
                            selectedOptions.put(selectedOption.getOptionName(), selectedOption.getOptionPrice());
                            selectedMenu.addOption(selectedOption);
                            System.out.println("추가된 옵션: " + selectedOption.getOptionName() + " (+" + selectedOption.getOptionPrice() + "원)");
                            System.out.println("현재 총 가격: " + selectedMenu.getTotalPrice() + "원");
                        } else {
                            System.out.println("잘못된 옵션 선택입니다. 다시 선택해 주세요.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해야 합니다. 다시 선택해 주세요.");
                }
            }

            // 옵션 정보와 가격 저장
            orderInfo.put("options", selectedOptions);
            orderInfo.put("price", selectedMenu.getTotalPrice());

            // 주문 정보 출력 (테스트용)
            System.out.println("\n=== 선택하신 주문 정보 ===");
            System.out.println("메뉴: " + orderInfo.get("menu"));
            System.out.println("HOT/ICE: " + orderInfo.get("temperature"));
            System.out.println("선택된 옵션: " + orderInfo.get("options"));
            System.out.println("총 가격: " + orderInfo.get("price"));

            // 추가 주문 여부 확인
            boolean validInput = false;
            while (!validInput) {
                System.out.println("\n추가 주문하시겠습니까? (Y/N)");
                String continueOrder = scanner.nextLine().trim().toUpperCase();

                if (continueOrder.equals("Y")) {
                    validInput = true;
                    // 추가 주문을 위해 while 루프 계속
                } else if (continueOrder.equals("N")) {
                    validInput = true;
                    return; // 프로그램 종료
                } else {
                    System.out.println("Y 또는 N만 입력 가능합니다. 다시 선택해 주세요.");
                }
            }
        }
    }
}