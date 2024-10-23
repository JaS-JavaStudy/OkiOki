package menu;
import java.util.*;

public class Option {
    private String optionName;
    private int optionPrice;

    // 옵션 목록을 저장하는 정적 Map
    private static Map<Integer, Option> optionMap = new HashMap<>();

    public Option(String optionName, int optionPrice) {
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }

    // 옵션 초기화
    static {
        optionMap.put(2, new Option("샷 추가", 500));
        optionMap.put(3, new Option("헤이즐넛 시럽 추가", 500));
        optionMap.put(4, new Option("우유 추가", 500));
        optionMap.put(5, new Option("휘핑 추가", 500));
        optionMap.put(6, new Option("펄 추가", 700));
    }

    // 옵션 목록 출력
    public static void displayOptions() {
        System.out.println("옵션:");
        for (Map.Entry<Integer, Option> entry : optionMap.entrySet()) {
            Option option = entry.getValue();
            System.out.println(entry.getKey() + ". " + option.getOptionName() + "(+" + option.getOptionPrice() + "원)");
        }
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
