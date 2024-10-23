package menu;
import java.util.*;

public class Menu {
    private String menuName;
    private int price;
    private int totalPrice;

    // 메뉴판을 저장하는 정적 Map
    private static Map<Integer, Menu> menuMap = new HashMap<>();

    // 생성자
    public Menu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
        this.totalPrice = price;
    }

    // 메뉴판 초기화
    static {
        menuMap.put(1, new Menu("아메리카노", 1500));
        menuMap.put(2, new Menu("디카페인 아메리카노", 2500));
        menuMap.put(3, new Menu("카페라떼", 2900));
        menuMap.put(4, new Menu("카페모카", 3500));
        menuMap.put(5, new Menu("카라멜마끼아또", 3300));
        menuMap.put(6, new Menu("바닐라라떼", 3300));
        menuMap.put(7, new Menu("돌체라떼", 3800));
        menuMap.put(6, new Menu("딸기라떼", 3800));
        menuMap.put(9, new Menu("밀크티", 3800));
        menuMap.put(10, new Menu("그린티라떼", 3500));
        menuMap.put(11, new Menu("더블초코라떼", 3500));
        menuMap.put(12, new Menu("고구마라떼", 3500));
        menuMap.put(13, new Menu("곡물라떼", 3300));
        menuMap.put(14, new Menu("복숭아주스", 3800));
        menuMap.put(15, new Menu("키위주스", 3800));
        menuMap.put(16, new Menu("망고스무디", 3800));
        menuMap.put(17, new Menu("블루베리스무디", 3800));
        menuMap.put(18, new Menu("플레인 요거트스무디", 4000));
        menuMap.put(19, new Menu("망고 요거트스무디", 4000));
        menuMap.put(20, new Menu("블루베리 요거트스무디", 4000));
        menuMap.put(21, new Menu("레몬에이드", 3500));
        menuMap.put(22, new Menu("패션후르츠에이드", 3500));
        menuMap.put(23, new Menu("자몽에이드", 3500));
        menuMap.put(24, new Menu("캐모마일티", 2500));
        menuMap.put(25, new Menu("페퍼민트티", 2500));
        menuMap.put(26, new Menu("얼그레이", 2500));
        menuMap.put(27, new Menu("자몽허니블랙티", 3800));
        menuMap.put(28, new Menu("민트초코오레오프라페", 4000));
        menuMap.put(29, new Menu("리얼초코자바칩프라페", 4000));
        menuMap.put(30, new Menu("플레인밀크쉐이크", 4200));
        menuMap.put(31, new Menu("쿠키밀크쉐이크", 4500));
        menuMap.put(32, new Menu("팥절미밀크쉐이크", 4500));
    }

    // 메뉴판 출력
    public static void displayMenu() {
        System.out.println("메뉴판:");
        for (Map.Entry<Integer, Menu> entry : menuMap.entrySet()) {
            Menu menu = entry.getValue();
            int number = entry.getKey();
            // 번호를 2자리로 맞추고, 메뉴명은 30칸으로 늘림
            System.out.printf("%2d. %-30s%,7d원\n", number, menu.getMenuName(), menu.getPrice());
        }
    }

    // 선택한 메뉴 반환
    public static Menu getMenu(int menuNumber) {
        return menuMap.get(menuNumber);
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    // 옵션 추가 시 가격 증가
    public void addOption(Option option) {
        this.totalPrice += option.getOptionPrice();
    }
    // 메뉴판 (메뉴 이름과 가격)
    // 메뉴를 선택하면 가격이 총 가격에 더해져야함

    // 옵션들

    /*
    1. 핫 or 아이스 (가격 변동 없음)
    2. 1샷 추가 (+500원)
    3. 휘핑 추가 (+500원)
    4. 펄 추가 (+700원)
    5. 우유 추가 (+500원)
    6. 헤이즐넛 시럽 추가 (+500원)
     */
    // 각 옵션은 사용자가 그만 입력할 때까지 추가할 수 있으며
    // 옵션이 추가될 때마다 총 가격에 옵션 가격 추가
}
