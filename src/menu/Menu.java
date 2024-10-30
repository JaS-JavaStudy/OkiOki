package menu;
import java.util.*;

public class Menu {
    private String menuName;
    private int price;
    private int totalPrice;
    private Category category;

    // 생성자
    public Menu(String menuName, int price, Category category) {
        this.menuName = menuName;
        this.price = price;
        this.totalPrice = price;
        this.category = category;
    }

    // 카테고리 enum 정의
    public enum Category {
        COFFEE("Coffee"),
        BEVERAGE("Beverage"),
        JUICE("Juice"),
        SMOOTHIE("Smoothie"),
        ADE("Ade"),
        TEA("Tea"),
        FRAPPE("Frappe"),
        MILK_SHAKE("Milk Shake");

        private final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // 메뉴판을 저장하는 정적 Map
    private static final Map<Integer, Menu> menuMap = new LinkedHashMap<>();
    private static final Set<Integer> iceOnlyMenus = new HashSet<>(); // 아이스만 가능한 메뉴 목록

    // 메뉴판 초기화
    static {
        menuMap.put(1, new Menu("SSAFEE", 2500, Category.COFFEE));
        menuMap.put(2, new Menu("아메리카노", 1500, Category.COFFEE));
        menuMap.put(3, new Menu("디카페인 아메리카노",2500, Category.COFFEE));
        menuMap.put(4, new Menu("카페라떼", 2900, Category.COFFEE));
        menuMap.put(5, new Menu("카페모카", 3500, Category.COFFEE));
        menuMap.put(6, new Menu("카라멜마끼아또", 3300, Category.COFFEE));
        menuMap.put(7, new Menu("바닐라라떼", 3300, Category.COFFEE));
        menuMap.put(8, new Menu("돌체라떼", 3800, Category.COFFEE));
        menuMap.put(9, new Menu("딸기라떼", 3800, Category.BEVERAGE));
        menuMap.put(10, new Menu("밀크티", 3800, Category.BEVERAGE));
        menuMap.put(11, new Menu("그린티라떼", 3500, Category.BEVERAGE));
        menuMap.put(12, new Menu("더블초코라떼", 3500, Category.BEVERAGE));
        menuMap.put(13, new Menu("고구마라떼", 3500, Category.BEVERAGE));
        menuMap.put(14, new Menu("곡물라떼", 3300, Category.BEVERAGE));
        menuMap.put(15, new Menu("복숭아주스", 3800, Category.JUICE));
        menuMap.put(16, new Menu("키위주스", 3800, Category.JUICE));
        menuMap.put(17, new Menu("망고스무디", 3800, Category.SMOOTHIE));
        menuMap.put(18, new Menu("블루베리스무디", 3800, Category.SMOOTHIE));
        menuMap.put(19, new Menu("플레인 요거트스무디", 4000, Category.SMOOTHIE));
        menuMap.put(20, new Menu("망고 요거트스무디", 4000, Category.SMOOTHIE));
        menuMap.put(21, new Menu("블루베리 요거트스무디", 4000, Category.SMOOTHIE));
        menuMap.put(22, new Menu("레몬에이드", 3500, Category.ADE));
        menuMap.put(23, new Menu("패션후르츠에이드", 3500, Category.ADE));
        menuMap.put(24, new Menu("자몽에이드", 3500, Category.ADE));
        menuMap.put(25, new Menu("캐모마일티", 2500, Category.TEA));
        menuMap.put(26, new Menu("페퍼민트티", 2500, Category.TEA));
        menuMap.put(27, new Menu("얼그레이", 2500, Category.TEA));
        menuMap.put(28, new Menu("자몽허니블랙티", 3800, Category.TEA));
        menuMap.put(29, new Menu("민트초코오레오프라페", 4000, Category.FRAPPE));
        menuMap.put(30, new Menu("리얼초코자바칩프라페", 4000, Category.FRAPPE));
        menuMap.put(31, new Menu("플레인밀크쉐이크", 4200, Category.MILK_SHAKE));
        menuMap.put(32, new Menu("쿠키밀크쉐이크", 4500, Category.MILK_SHAKE));
        menuMap.put(33, new Menu("팥절미밀크쉐이크", 4500, Category.MILK_SHAKE));

        // 아이스만 가능한 메뉴 추가
        iceOnlyMenus.add(9);
        iceOnlyMenus.add(15);
        iceOnlyMenus.add(16);
        iceOnlyMenus.add(17);
        iceOnlyMenus.add(18);
        iceOnlyMenus.add(19);
        iceOnlyMenus.add(20);
        iceOnlyMenus.add(21);
        iceOnlyMenus.add(22);
        iceOnlyMenus.add(23);
        iceOnlyMenus.add(24);
        iceOnlyMenus.add(29);
        iceOnlyMenus.add(30);
        iceOnlyMenus.add(31);
        iceOnlyMenus.add(32);
        iceOnlyMenus.add(33);
    }

    // 메뉴판 출력
    public static void displayMenu() {
        System.out.println("\n=============== CAFE MENU ===============");

        Category currentCategory = null;

        for (Map.Entry<Integer, Menu> entry : menuMap.entrySet()) {
            Menu menu = entry.getValue();

            // 카테고리가 바뀔 때마다 카테고리 제목 출력
            if (currentCategory != menu.getCategory()) {
                currentCategory = menu.getCategory();
                System.out.println("\n━━━━━━━━━━━━━━━━ " + currentCategory.getDisplayName() + " ━━━━━━━━━━━━━━━━");
            }

            // 메뉴 정보 출력
            System.out.printf("%2d. %-30s%,7d원\n", entry.getKey(), menu.getMenuName(), menu.getPrice());
        }
        System.out.println("\n========================================");
    }

    // 아이스 전용 메뉴인지 확인하는 메서드
    public boolean isIceOnly() {
        return iceOnlyMenus.contains(getMenuNumber()); // 현재 메뉴가 아이스 전용 메뉴인지 체크
    }

    public int getMenuNumber() {
       for (Map.Entry<Integer, Menu> entry : menuMap.entrySet()) {
           if (entry.getValue() == this) {
               return entry.getKey();
           }
       }
       return -1;
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

    public Category getCategory() {
        return category;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    // 옵션 추가 시 가격 증가
    public void addOption(Option option) {
        this.totalPrice += option.getOptionPrice();
    }
}
