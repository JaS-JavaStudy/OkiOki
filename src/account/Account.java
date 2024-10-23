package account;

import java.util.ArrayList;

public class Account {
    // 유저생성 클래스
    String name;
    String userID;
    int classroom;
    int mypoint;
    ArrayList<TempCoffee> cart; // 장바구니
    ArrayList<TempCoffee> orderList; // 주문내역

    public Account(String name , String userID , int classroom){
        this.name = name;
        this.userID = userID;
        this.classroom = classroom;
        this.mypoint = 0;
        this.cart = new ArrayList<TempCoffee>();
        this.orderList = new ArrayList<TempCoffee>();
    }
}
