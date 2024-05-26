package onlineshop.controllers;

import onlineshop.merchandise.Cart;
import onlineshop.merchandise.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class FunctionController {

    @Autowired
    Cart cart;

    protected void getCartItems(Model view) {
        List<CartItem> cartItems = cart.getItems();
        view.addAttribute("cartItems", cartItems);
        view.addAttribute("numOfCartItems", cart.getNumOfItems());
        view.addAttribute("grandTotal", cart.getGrandTotal());
    }
}

