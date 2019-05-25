package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.models.Subscription;
import com.netcracker.edu.fapi.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public Response subscribe(Subscription subscription, String email) {
       RestTemplate restTemplate = new RestTemplate();
       subscription.setEmail(email);
       return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions/user", subscription, Response.class).getBody();
    }


    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/user?email=" + email, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public Response deleteUserSubscription(String email, long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions/user?email=" + email + "&id=" + id);
        return new Response("ok");
    }

    @Override
    public Response setSale(Subscription subscription) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/subscriptions/sale", subscription, Response.class);
        return new Response("ok");
    }
}
