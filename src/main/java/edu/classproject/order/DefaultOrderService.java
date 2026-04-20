package edu.classproject.order;

import edu.classproject.common.IdGenerator;
import edu.classproject.common.OrderStatus;
import edu.classproject.notification.NotificationService;
import edu.classproject.payment.PaymentResult;
import edu.classproject.payment.PaymentService;
import edu.classproject.restaurant.MenuItem;
import edu.classproject.restaurant.Restaurant;
import edu.classproject.restaurant.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public DefaultOrderService(OrderRepository orderRepository,
                               RestaurantService restaurantService,
                               PaymentService paymentService,
                               NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    @Override
    public Order placeOrder(OrderRequest request) {
        Restaurant restaurant = restaurantService.getRestaurant(request.restaurantId());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRequestItem requestItem : request.items()) {
            MenuItem menuItem = restaurant.getMenuItem(requestItem.menuItemId());
            orderItems.add(new OrderItem(
                    menuItem.itemId(),
                    menuItem.name(),
                    menuItem.price(),
                    requestItem.quantity()
            ));
        }

        Order order = new Order(
                IdGenerator.nextId("ORD"),
                request.customerId(),
                request.restaurantId(),
                orderItems
        );

        order.setStatus(OrderStatus.PLACED);

        PaymentResult paymentResult = paymentService.processPayment(request.customerId(), order.totalAmount());
        if (!paymentResult.success()) {
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            orderRepository.save(order);
            notificationService.notifyUser(request.customerId(), "Payment failed: " + paymentResult.message());
            return order;
        }

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        notificationService.notifyUser(request.customerId(), "Order confirmed: " + order.orderId());
        return order;
    }

    @Override
    public Order getOrder(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    @Override
    public void updateStatus(String orderId, String status) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
    }
}
