package onlineshop.controllers;

import onlineshop.merchandise.Cart;
import onlineshop.merchandise.CartItem;
import onlineshop.merchandise.Order;
import onlineshop.merchandise.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    private final Cart cart;

    @Autowired
    public OrderController(OrderService orderService, Cart cart) {
        this.orderService = orderService;
        this.cart = cart;
    }



    @PostMapping("/create-order")
    public String createOrder(@RequestParam Map<String, String> params) {
        Order order = new Order();
        order.setFirstname(params.get("firstname"));
        order.setLastname(params.get("lastname"));
        order.setCompanyname(params.get("companyname"));
        order.setAddress1(params.get("address1"));
        order.setAddress2(params.get("address2"));
        order.setCity(params.get("city"));
        order.setZip(params.get("zip"));
        order.setPhone(params.get("phone"));
        order.setEmail(params.get("email"));
        order.setNotes(params.get("notes"));
        order.setPaymentMethod(params.get("listGroupRadios"));
        List<CartItem> cartItems = cart.getItems();
        order.setCartItems(cartItems);
        orderService.createOrder(order);
        Order createdOrder = orderService.createOrder(order);
        return "redirect:/order/" + createdOrder.getId();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{orderId}")
    public String getOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("cartItems", order.getCartItems());
        System.out.println(order.getCartItems());
        return "orderDetails";
    }
}

