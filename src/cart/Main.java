package cart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("=== 오키오키 커피 주문 키오스크 ===");

        Options options = new Options(reader);  // BufferedReader 전달
        Bucket bucket = new Bucket(reader);     // BufferedReader 전달

        boolean keepOrder = true;
        try {
            while (keepOrder) {
                // 4. 옵션 선택 => 장바구니에 추가
                options.selectOption();
                bucket.addToBucket("아메리카노", options);  // 예시로 아메리카노 추가

                // 추가된 음료와 옵션 확인
                System.out.println("현재 음료 상태:");
                bucket.displayCurrentItem("아메리카노", options);

                // 추가로 메뉴를 선택할지 물어보쟝
                System.out.print("주문을 계속 하시겠습니까? (Y/N): ");
                String answer = reader.readLine().trim();

                if (answer.equalsIgnoreCase("N")) {
                    keepOrder = false;
                }
            }

            // 5. 장바구니 확인, 수정(U), 삭제(D)
            bucket.displayBucket();

            System.out.print("장바구니를 수정하시겠습니까? (Y/N): ");
            String modifyAnswer = reader.readLine().trim();

            if (modifyAnswer.equalsIgnoreCase("Y")) {
                bucket.modifyBucket();
            }

            System.out.print("장바구니에서 항목을 삭제하시겠습니까? (Y/N): ");
            String deleteAnswer = reader.readLine().trim();

            if (deleteAnswer.equalsIgnoreCase("Y")) {
                bucket.removeFromBucket();
            }

        } catch (IOException e) {
            System.out.println("입력 중 오류가 발생했습니다: " + e.getMessage());
        }

        System.out.println("이용해 주셔서 감사합니다!");
    }
}
