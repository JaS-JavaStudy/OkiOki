import account.Account;
import account.Login;
import cart.Bucket;
import menu.Menu;
import menu.Option;
import menu.Temperature;
import payment.paymentMain;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> orderInfo = new HashMap<>();
        System.out.println("=== 오키오키 커피 주문 키오스크 ===");
        Bucket new_cart = new Bucket();

        Account user1 = Login.Login();

        boolean is_ordering = true;
        while (is_ordering) {
            Menu.displayMenu();
            int menuChoice = -1;
            Menu selectedMenu = null;

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

            orderInfo.put("menu", selectedMenu.getMenuName());

            int tempChoice;
            if (selectedMenu.isIceOnly()) {
                tempChoice = 1;
                System.out.println(selectedMenu.getMenuName() + "는 ☃️ICE ONLY입니다.");
            } else {
                while (true) {
                    Temperature.displayTemperature();
                    System.out.println("선택해 주세요 (0: 🔥HOT, 1: ☃️ICE):");
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
            orderInfo.put("temperature", tempChoice);

            Map<String, Integer> selectedOptions = new HashMap<>();
            int optionChoice = -1;
            while (optionChoice != 0) {
                Option.displayOptions();
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

            orderInfo.put("options", selectedOptions);
            orderInfo.put("price", selectedMenu.getTotalPrice());

            new_cart.add_order(orderInfo);

            boolean validInput = false;
            while (!validInput) {

                System.out.println("\n추가 주문하시겠습니까? (Y/N)");
                System.out.println("현재 장바구니 확인하시려면 'C'를 입력해 주세요.");
                String continueOrder = scanner.nextLine().trim().toUpperCase();

                if (continueOrder.equals("Y")) {
                    validInput = true;
                } else if (continueOrder.equals("N")) {
                    boolean is_editing = true;
                    while (is_editing) {
                        System.out.println("\n장바구니 메뉴:");
                        System.out.println("1. 장바구니 음료 삭제");
                        System.out.println("2. 옵션 삭제");
                        System.out.println("3. 결제하기");
                        System.out.print("선택하세요: ");

                        int editChoice = Integer.parseInt(scanner.nextLine());
                        switch (editChoice) {
                            case 1:
                                new_cart.delete_order();
                                break;
                            case 2:
                                new_cart.delete_options();
                                break;
                            case 3:
                                is_editing = false;
                                paymentMain.processPayment(new_cart.getCart());
                                break;
                            default:
                                System.out.println("잘못된 선택입니다.");
                                break;
                        }
                    }
                    validInput = true;
                    is_ordering = false;
                } else if (continueOrder.equals("C")) {
                    new_cart.viewCart();
                } else {
                    System.out.println("Y, N 또는 C만 입력 가능합니다. 다시 선택해 주세요.");
                }
            }
        }
    }
}
