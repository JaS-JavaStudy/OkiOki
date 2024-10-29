package menu;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.*;

public class TempMenuSelect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        // 선택한 메뉴 정보 출력
        System.out.println("선택한 메뉴: " + selectedMenu.getMenuName() + ", 가격: " + selectedMenu.getPrice() + "원");

        // 2. 온도 선택
        int tempChoice = -1;

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

        // 3. 옵션 선택
        int optionChoice = -1;
        while (optionChoice != 0) {
            Option.displayOptions();  // 옵션 목록 출력

            System.out.println("옵션 선택을 종료하려면 0을 입력하세요.");
            try {
                optionChoice = Integer.parseInt(scanner.nextLine());

                if (optionChoice != 0) {
                    Option selectedOption = Option.getOption(optionChoice);
                    if (selectedOption != null) {
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

        // 4. 최종 주문 내역 출력
        System.out.println("\n====== 주문 내역 ======");
        System.out.println("메뉴: " + selectedMenu.getMenuName() + " (" + Temperature.getTemperatureName(tempChoice) + ")");
        System.out.println("최종 가격: " + selectedMenu.getTotalPrice() + "원");
        System.out.println("======================");
    }
}