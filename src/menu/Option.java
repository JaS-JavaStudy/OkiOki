package menu;
import java.util.*;

public class Option {
    private final String optionName;
    private final int optionPrice;

    // 옵션 목록을 저장하는 정적 Map
    private static Map<Integer, Option> optionMap = new LinkedHashMap<>();

    public Option(String optionName, int optionPrice) {
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }

    // 옵션 초기화
    static {
        optionMap.put(1, new Option("☕샷 추가", 500));
        optionMap.put(2, new Option("🥜헤이즐넛 시럽 추가", 500));
        optionMap.put(3, new Option("🥛우유 추가", 500));
        optionMap.put(4, new Option("☁️휘핑 추가", 500));
        optionMap.put(5, new Option("🧋펄 추가", 700));
    }

    // 옵션 목록 출력
    public static void displayOptions() {
        System.out.println("\n============== OPTIONS ==============");

        for (Map.Entry<Integer, Option> entry : optionMap.entrySet()) {
            Option option = entry.getValue();
            System.out.println(entry.getKey() + ". " + option.getOptionName() + "(+" + option.getOptionPrice() + "원)");
        }
        System.out.println("=====================================");
    }

    // 선택한 옵션 반환
    public static Option getOption(int optionNumber) {
        return optionMap.get(optionNumber);
    }

    public String getOptionName() {
        return optionName;
    }

    public int getOptionPrice() {
        return optionPrice;
    }
}
