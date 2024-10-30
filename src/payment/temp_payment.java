package payment;

import java.util.ArrayList;
import java.util.Map;

public class temp_payment {
    public static void temp_pay(ArrayList<Map<String, Object>> new_cart){
        int total_price = 0;
        for (Map<String, Object> order : new_cart){
            total_price += (Integer) order.get("price");
        }

        System.out.println("결제!");
        System.out.println(total_price);
        System.out.println("결제끝");



        System.out.println("영수증");
        for (Map<String, Object> order : new_cart){
            System.out.println("-----");
            System.out.println("메뉴: " + order.get("menu"));
            System.out.println("HOT/ICE: " + order.get("temperature"));
            System.out.println("선택된 옵션: " + order.get("options"));
            System.out.println("총 가격: " + order.get("price"));
            System.out.println("-----");
        }
    }
}
