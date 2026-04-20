package edu.classproject.bootstrap;

import edu.classproject.common.Money;
import edu.classproject.notification.ConsoleNotificationService;
import edu.classproject.notification.NotificationService;
import edu.classproject.order.DefaultOrderService;
import edu.classproject.order.InMemoryOrderRepository;
import edu.classproject.order.Order;
import edu.classproject.order.OrderRequest;
import edu.classproject.order.OrderRequestItem;
import edu.classproject.order.OrderService;
import edu.classproject.payment.MockPaymentService;
import edu.classproject.payment.PaymentService;
import edu.classproject.restaurant.DefaultRestaurantService;
import edu.classproject.restaurant.InMemoryRestaurantRepository;
import edu.classproject.restaurant.MenuItem;
import edu.classproject.restaurant.Restaurant;
import edu.classproject.restaurant.RestaurantService;
import edu.classproject.user.DefaultUserService;
import edu.classproject.user.InMemoryUserRepository;
import edu.classproject.user.User;
import edu.classproject.user.UserService;

import java.util.List;

public class DemoApplication {
    public static void main(String[] args) {
        UserService userService = new DefaultUserService(new InMemoryUserRepository());
        RestaurantService restaurantService = new DefaultRestaurantService(new InMemoryRestaurantRepository());
        PaymentService paymentService = new MockPaymentService();
        NotificationService notificationService = new ConsoleNotificationService();
        OrderService orderService = new DefaultOrderService(
                new InMemoryOrderRepository(),
                restaurantService,
                paymentService,
                notificationService
        );

        User customer = userService.registerCustomer("Alex", "alex@example.edu");

        Restaurant restaurant = restaurantService.createRestaurant("Campus Bites");
        MenuItem burger = restaurantService.addMenuItem(restaurant.restaurantId(), "Burger", Money.of(8.50));
        MenuItem fries = restaurantService.addMenuItem(restaurant.restaurantId(), "Fries", Money.of(3.00));

        OrderRequest orderRequest = new OrderRequest(
                customer.userId(),
                restaurant.restaurantId(),
                List.of(
                        new OrderRequestItem(burger.itemId(), 2),
                        new OrderRequestItem(fries.itemId(), 1)
                )
        );

        Order order = orderService.placeOrder(orderRequest);

        System.out.println("Order ID: " + order.orderId());
        System.out.println("Status: " + order.status());
        System.out.println("Total: " + order.totalAmount());
    }
}
