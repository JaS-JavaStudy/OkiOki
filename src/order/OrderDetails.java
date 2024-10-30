package order;

import java.util.Map;

public class OrderDetails {
    private String menu;
    private double price; // 기본 가격
    private int temperature; // 0: hot, 1: ice
    private Map<String, Object> options; // 옵션: Map<String, Object>

    public OrderDetails(String menu, double price, int temperature, Map<String, Object> options) {
        this.menu = menu;
        this.price = price;
        this.temperature = temperature;
        this.options = options;
    }

    public double calculateTotalPrice() {
        double totalPrice = price; // 기본 가격
        for (Object optionPrice : options.values()) {
            if (optionPrice instanceof Double) {
                totalPrice += (Double) optionPrice; // 옵션 가격을 추가
            }
        }
        return totalPrice; // 기본 가격 + 옵션 가격
    }

    public String getMenu() {
        return menu;
    }

    public double getBasePrice() { // 기본 가격 반환
        return price;
    }

    public int getTemperature() {
        return temperature;
    }

    public Map<String, Object> getOptions() { // 옵션 반환
        return options;
    }
}
