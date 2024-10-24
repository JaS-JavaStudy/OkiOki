package menu;
import java.util.*;

public class TempMenuSelect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 메뉴 선택
        Menu.displayMenu();

        System.out.println("메뉴를 선택하세요: ");
        int menuChoice = scanner.nextInt();

        Menu selectedMenu = Menu.getMenu(menuChoice);
        if (selectedMenu == null) {
            System.out.println("잘못된 메뉴 선택입니다.");
            return;
        }

        // 선택한 메뉴 정보 출력
        System.out.println("선택한 메뉴: " + selectedMenu.getMenuName() + ", 기본 가격: " + selectedMenu.getPrice() + "원");

        // 2. 온도 선택
        Temperature.displayTemperature();
        System.out.println("온도를 선택하세요 (0: HOT, 1: ICE): ");
        int tempChoice = scanner.nextInt();

        if (!Temperature.isValidTemperature(tempChoice)) {
            System.out.println("잘못된 온도 선택입니다.");
            return;
        }

        System.out.println(Temperature.getTemperatureName(tempChoice) + "으로 선택되었습니다.");

        // 3. 옵션 선택
        int optionChoice = -1;
        while (optionChoice != 0) {
            Option.displayOptions();  // 옵션 목록 출력

            System.out.println("옵션 선택을 종료하려면 0을 입력하세요.");
            optionChoice = scanner.nextInt();

            if (optionChoice != 0) {
                Option selectedOption = Option.getOption(optionChoice);
                if (selectedOption != null) {
                    selectedMenu.addOption(selectedOption);
                    System.out.println("추가된 옵션: " + selectedOption.getOptionName() + " (+" + selectedOption.getOptionPrice() + "원)");
                    System.out.println("현재 총 가격: " + selectedMenu.getTotalPrice());
                } else {
                    System.out.println("잘못된 옵션 번호입니다.");
                }
            }
        }

        // 4. 최종 주문 내역 출력
        System.out.println("\n====== 주문 내역 ======");
        System.out.println("메뉴: " + selectedMenu.getMenuName() +
                " (" + Temperature.getTemperatureName(tempChoice) + ")");
        System.out.println("최종 가격: " + selectedMenu.getTotalPrice() + "원");
        System.out.println("====================");
    }
}
