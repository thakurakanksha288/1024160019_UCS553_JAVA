package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.service.OrderService;
import food.utility.OrderUtility;

public class Main {

    public static void main(String[] args) {

        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "Akansha", 500.0);
        orders[1] = new PremiumOrder(102, "Rohit", 800.0);
        orders[2] = new RegularOrder(103, "Simran", 350.0);
        orders[3] = new PremiumOrder(104, "Vikram", 1200.0);
        orders[4] = new RegularOrder(105, "Neha", 650.0);
        orders[5] = new PremiumOrder(106, "Karan", 950.0);

        OrderService service = new OrderService();

        for (FoodOrder order : orders) {
            if (!OrderUtility.validateAmount(order.getAmount())
                    || !OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Invalid order skipped: " + order.getOrderId());
                continue;
            }

            System.out.println("\n===== Order " + order.getOrderId() + " =====");
            service.displayBill(order);
            service.displayDiscount(order);
            service.displayDeliveryCharge(order);
            service.displayFinalPayableAmount(order);

            System.out.println(OrderUtility.generateOrderSummary(order));
        }

        System.out.println();
        FoodOrder.displayTotalOrders();
    }
}
