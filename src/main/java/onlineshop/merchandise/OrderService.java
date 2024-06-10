package onlineshop.merchandise;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final Cart cart;

    public OrderService(Cart cart) {
        this.cart = cart;
    }

    public Order createOrder(Order order) {
        orders.add(order);
        cart.clearCart();
        return order;
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long orderId) {
        return orders.stream().filter(order -> order.getId().equals(orderId)).findFirst().orElse(null);
    }
}
