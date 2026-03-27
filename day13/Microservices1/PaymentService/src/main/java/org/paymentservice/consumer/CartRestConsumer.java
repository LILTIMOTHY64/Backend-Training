package org.paymentservice.consumer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartRestConsumer {
    @Autowired
    private DiscoveryClient discoveryClient;
    public String getCartData() {
        List<ServiceInstance> instances =
                discoveryClient.getInstances("CartService");
        if (instances.isEmpty()) {
            return "CART-SERVICE not available right now";
        }
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri() + "/cart/getData";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
