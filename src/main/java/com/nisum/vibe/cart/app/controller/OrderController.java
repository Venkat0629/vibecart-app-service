package com.nisum.vibe.cart.app.controller;


import com.nisum.vibe.cart.app.dto.ApiResponse;
import com.nisum.vibe.cart.app.dto.OrderDTO;
import com.nisum.vibe.cart.app.dto.SkuQuantityResponse;
import com.nisum.vibe.cart.app.service.impl.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vibe-cart/app/orders")
public class OrderController {

    private OrderService orderService;

    private final RestTemplate restTemplate;

    @Value("${scm.inventory.service.url}")
    private String INVENTORY_SERVICE_URL;

    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sku/total-quantity")
    public ResponseEntity<List<SkuQuantityResponse>> checkItemQuantity(@RequestBody List<Long> skuList) {
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(skuList);
        ResponseEntity<ApiResponse<List<Integer>>> response = restTemplate.exchange(INVENTORY_SERVICE_URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<List<Integer>>>() {
        });
        ApiResponse<List<Integer>> responseBody = response.getBody();
        List<SkuQuantityResponse> responseList = new ArrayList<>();
        for (int i = 0; i < skuList.size(); i++) {
            responseList.add(new SkuQuantityResponse(skuList.get(i), responseBody.getData().get(i)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
