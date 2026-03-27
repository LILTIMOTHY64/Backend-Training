package org.paymentservice.controller;

import org.paymentservice.consumer.CartRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {
    @Autowired
    private CartRestConsumer cartRestConsumer;
    @GetMapping("/payment/getData")
     public String getPaymentData(){
         String cartData = cartRestConsumer.getCartData();
         return "Returning data from PAYMENT SERVICE. " + cartData;
     }
}

