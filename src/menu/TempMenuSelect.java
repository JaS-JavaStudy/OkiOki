package menu;
import java.util.*;

public class TempMenuSelect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 메뉴판 출력
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

        // 옵션 선택
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
    }
}
