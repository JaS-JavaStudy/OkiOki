package cart;

import java.io.BufferedReader;
import java.io.IOException;

public class Options {
    private BufferedReader reader;

    private String[] options = { "🔥핫", "🧊아이스", "☕샷", "🥜헤이즐넛", "😶‍🌫️연하게"};
    private boolean[] selectedOptions = new boolean[options.length];

    public Options(BufferedReader reader) {
        this.reader = reader;
    }

    // 옵션 선택 메서드
    public void selectOption() throws IOException {
        System.out.println("옵션을 선택하세요:");

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        String input;
        do {
            System.out.print("옵션 번호를 입력하세요 (종료하려면 0 입력): ");
            input = reader.readLine().trim();
            int optionNum;

            try {
                optionNum = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("유효하지 않은 입력입니다. 다시 입력해주세요.");
                continue;
            }

            if (optionNum == 0) break;

            if (optionNum > 0 && optionNum <= options.length) {
                selectedOptions[optionNum - 1] = true;
                System.out.println(options[optionNum - 1] + " 선택됨.");
            } else {
                System.out.println("유효하지 않은 번호입니다. 다시 입력해주세요.");
            }
        } while (true);
    }

    public String[] getSelectedOptions() {
        return options;
    }

    public boolean[] getSelectedOptionStatus() {
        return selectedOptions;
    }
}
