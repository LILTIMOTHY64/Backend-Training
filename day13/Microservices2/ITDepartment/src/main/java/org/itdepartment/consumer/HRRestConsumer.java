package org.itdepartment.consumer;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HRRestConsumer {

    @Autowired
    private DiscoveryClient discoveryClient;

    public boolean getLeaveData() {
        List<ServiceInstance> instances =
                discoveryClient.getInstances("HR-SERVICE");
        if (instances.isEmpty()) {
            return false;
        }
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri() + "/hr/getLeaveData";
        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(url, boolean.class));
    }

}
