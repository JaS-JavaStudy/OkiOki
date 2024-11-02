package menu;

public class Temperature {
    private static final String[] NAMES = {"HOT", "ICE"};

    // 온도 선택지 출력
    public static void displayTemperature() {
        System.out.println("\n[🔥HOT / ☃️ICE]");
        for (int i = 0; i < NAMES.length; i++) {
            System.out.printf("(%d) %s%n", i, NAMES[i]);
        }
    }

    // 선택한 온도가 유효한지 체크
    public static boolean isValidTemperature(int temperature) {
        return temperature == 0 || temperature == 1;
    }

    // 온도 이름 반환
    public static String getTemperatureName(int temperature) {
        if (!isValidTemperature(temperature)) {
            return "선택 안됨";
        }
        return NAMES[temperature];
    }
}
