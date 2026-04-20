package edu.classproject.order;

import edu.classproject.common.Money;
import edu.classproject.common.OrderStatus;
import edu.classproject.notification.NotificationService;
import edu.classproject.payment.PaymentResult;
import edu.classproject.payment.PaymentService;
import edu.classproject.restaurant.DefaultRestaurantService;
import edu.classproject.restaurant.InMemoryRestaurantRepository;
import edu.classproject.restaurant.MenuItem;
import edu.classproject.restaurant.Restaurant;
import edu.classproject.restaurant.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultOrderServiceTest {
    private RestaurantService restaurantService;
    private InMemoryOrderRepository orderRepository;
    private CapturingNotificationService notificationService;

    @BeforeEach
    void setUp() {
        restaurantService = new DefaultRestaurantService(new InMemoryRestaurantRepository());
        orderRepository = new InMemoryOrderRepository();
        notificationService = new CapturingNotificationService();
    }

    @Test
    void placeOrder_shouldConfirmOrder_whenPaymentSucceeds() {
        Restaurant restaurant = restaurantService.createRestaurant("Test Restaurant");
        MenuItem item = restaurantService.addMenuItem(restaurant.restaurantId(), "Pasta", Money.of(10.00));

        PaymentService paymentService = (userId, amount) -> PaymentResult.approved("TXN-1");

        DefaultOrderService orderService = new DefaultOrderService(
                orderRepository,
                restaurantService,
                paymentService,
                notificationService
        );

        Order order = orderService.placeOrder(new OrderRequest(
                "USR-1",
                restaurant.restaurantId(),
                List.of(new OrderRequestItem(item.itemId(), 2))
        ));

        assertEquals(OrderStatus.CONFIRMED, order.status());
        assertEquals(1, notificationService.messages.size());
        assertEquals(true, notificationService.messages.get(0).contains("confirmed"));
    }

    @Test
    void placeOrder_shouldMarkPaymentFailed_whenPaymentDeclines() {
        Restaurant restaurant = restaurantService.createRestaurant("Test Restaurant");
        MenuItem item = restaurantService.addMenuItem(restaurant.restaurantId(), "Pasta", Money.of(10.00));

        PaymentService paymentService = (userId, amount) -> PaymentResult.declined("Card declined");

        DefaultOrderService orderService = new DefaultOrderService(
                orderRepository,
                restaurantService,
                paymentService,
                notificationService
        );

        Order order = orderService.placeOrder(new OrderRequest(
                "USR-1",
                restaurant.restaurantId(),
                List.of(new OrderRequestItem(item.itemId(), 1))
        ));

        assertEquals(OrderStatus.PAYMENT_FAILED, order.status());
        assertEquals(1, notificationService.messages.size());
        assertEquals(true, notificationService.messages.get(0).contains("Payment failed"));
    }

    private static class CapturingNotificationService implements NotificationService {
        private final List<String> messages = new ArrayList<>();

        @Override
        public void notifyUser(String userId, String message) {
            messages.add(userId + ":" + message);
        }
    }
}
