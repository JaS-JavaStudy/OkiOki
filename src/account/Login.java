package account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
    public static Account Login() throws IOException {
        // 로그인 전용 클래스
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("이름입력: ");
        String name = br.readLine();
        System.out.println("ID입력: ");
        String userID = br.readLine();
        System.out.println("반 선택: ");
        int classroom = Integer.parseInt(br.readLine());

        Account user1 = new Account(name,userID,classroom);

        return user1;
    }
}
