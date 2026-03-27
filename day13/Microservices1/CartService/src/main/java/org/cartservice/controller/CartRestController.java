package org.cartservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestController {
    @GetMapping("/cart/getData")
    public String getCartData(){
        return "Returning data from CART SERVICE.";
    }
}
