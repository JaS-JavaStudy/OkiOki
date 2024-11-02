package account;

import java.io.IOException;

public class TempAccountMain {
    public static void main(String[] args) throws IOException {
        // 테스트용 실행파일

        // 0. 로그인 시작화면 출력 및 유저 인스턴스 생성
        Account user1 = Login.Login();

        // 1. 유저 정보 확인하기 예시
        System.out.println(user1.name);
        System.out.println(user1.userID);
        System.out.println(user1.classroom);
        System.out.println(user1.mypoint);
        System.out.println(user1.cart);
        System.out.println(user1.orderList);
        System.out.println("------✂-------");


        // 2. 테스트용 커피 인스턴스 생성
        TempCoffee coffee1 = new TempCoffee("아아", 3, true);
        TempCoffee coffee2 = new TempCoffee("헤이즐넛", 1, true);

        // 3. cart에 담기
        user1.cart.add(coffee1);
        user1.cart.add(coffee2);

        // 4. 출력해보기
        System.out.println(user1.cart);
        System.out.println("-------✂--------");

        // 5. cart속 내용 출력하기
        for (int i = 0; i < user1.cart.size(); i++) {
            System.out.println(user1.cart.get(i).drink);
            System.out.println(user1.cart.get(i).size);
            System.out.println(user1.cart.get(i).add_pearl);
        }

    }
}
