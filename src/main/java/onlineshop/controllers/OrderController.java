package onlineshop.controllers;

import onlineshop.merchandise.Cart;
import onlineshop.merchandise.CartItem;
import onlineshop.merchandise.Order;
import onlineshop.merchandise.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController extends FunctionController {

    @Autowired
    private OrderService orderService;
    private final Cart cart;

    @Autowired
    public OrderController(OrderService orderService, Cart cart) {
        this.orderService = orderService;
        this.cart = cart;
    }

//    @PostMapping("/create-order")
//    public String createOrder(@RequestParam Map<String, String> params, Model model) {
//        Order order = new Order();
//        order.setFirstname(params.get("firstname"));
//        order.setLastname(params.get("lastname"));
//        order.setCompanyname(params.get("companyname"));
//        order.setAddress1(params.get("address1"));
//        order.setAddress2(params.get("address2"));
//        order.setCity(params.get("city"));
//        order.setZip(params.get("zip"));
//        order.setPhone(params.get("phone"));
//        order.setEmail(params.get("email"));
//        order.setNotes(params.get("notes"));
//        order.setPaymentMethod(params.get("listGroupRadios"));
//        List<CartItem> cartItems = cart.getItems();
//        System.out.println("Cart contains " + cartItems.size() + " items before creating order.");
//
//        for (CartItem item : cartItems) {
//            System.out.println("CartItem: " + item.getArticleNo() + ", Title: " + item.getTitle() + ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
//        }
//
//        order.setCartItems(cartItems);
//
//        Order createdOrder = orderService.createOrder(order);
//        return "redirect:/order/" + createdOrder.getId();
//    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam Map<String, String> params, Model model) {
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

        // Create a copy of cart items to ensure independence from the cart
        List<CartItem> cartItemsCopy = new ArrayList<>();
        for (CartItem item : cartItems) {
            CartItem itemCopy = new CartItem(item); // Use copy constructor
            itemCopy.setQuantity(item.getQuantity()); // Copy the quantity
            cartItemsCopy.add(itemCopy);
        }

        order.setCartItems(cartItemsCopy);

        Order createdOrder = orderService.createOrder(order);
        return "redirect:/order/" + createdOrder.getId();
    }


    @GetMapping("/order/{orderId}")
    public String getOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return "orderNotFound";
        }
        System.out.println("Order ID: " + order.getId());
        List<CartItem> cartItems = order.getCartItemsOrder();
        System.out.println("Cart Items: " + cartItems.size());
        for (CartItem item : cartItems) {
            System.out.println("CartItem: " + item.getArticleNo() + ", Title: " + item.getTitle() + ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
        }

        model.addAttribute("order", order);
        model.addAttribute("cartItems", cartItems);
        return "orderDetails";
    }


    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

}
