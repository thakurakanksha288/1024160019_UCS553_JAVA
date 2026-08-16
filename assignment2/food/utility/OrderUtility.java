package food.utility;

import food.model.FoodOrder;
import food.model.Discountable;

public class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateOrderSummary(FoodOrder order) {
        double discount = 0.0;
        if (order instanceof Discountable) {
            discount = ((Discountable) order).applyDiscount();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("----- Order Summary -----\n");
        sb.append("Order ID       : ").append(order.getOrderId()).append("\n");
        sb.append("Customer       : ").append(order.getCustomerName()).append("\n");
        sb.append("Amount         : Rs.").append(order.getAmount()).append("\n");
        sb.append("Discount       : Rs.").append(discount).append("\n");
        sb.append("Delivery Charge: Rs.").append(order.calculateDeliveryCharge()).append("\n");
        sb.append("Final Payable  : Rs.").append(order.calculateFinalPayableAmount()).append("\n");
        sb.append("--------------------------");
        return sb.toString();
    }
}
