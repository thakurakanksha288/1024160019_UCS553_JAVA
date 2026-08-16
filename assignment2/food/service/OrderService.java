package food.service;

import food.model.FoodOrder;
import food.model.Discountable;

public class OrderService {

    public void displayBill(FoodOrder order) {
        System.out.println(order.toString());
    }

    public void displayDiscount(FoodOrder order) {
        double discount = 0.0;
        if (order instanceof Discountable) {
            discount = ((Discountable) order).applyDiscount();
        }
        System.out.println("Discount: Rs." + discount);
    }

    public void displayDeliveryCharge(FoodOrder order) {
        System.out.println("Delivery Charge: Rs." + order.calculateDeliveryCharge());
    }

    public void displayFinalPayableAmount(FoodOrder order) {
        System.out.println("Final Payable Amount: Rs." + order.calculateFinalPayableAmount());
    }
}
