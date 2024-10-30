package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class temp_bucket01 {
    ArrayList<Map<String, Object>> cart;
    public temp_bucket01(){
        this.cart = new ArrayList<>();
    }

    public void add_order(Map<String, Object> orderInfo){
        Map<String, Object> newOrder = new HashMap<>(orderInfo);
        cart.add(newOrder);
    }
    public void display_bucket(){
        for (Map<String, Object> order : cart){
            System.out.println("-----");
            System.out.println("메뉴: " + order.get("menu"));
            System.out.println("HOT/ICE: " + order.get("temperature"));
            System.out.println("선택된 옵션: " + order.get("options"));
            System.out.println("총 가격: " + order.get("price"));
            System.out.println("-----");
        }
    }
}
