package com.nisum.vibe.cart.app.service.impl;

import com.nisum.vibe.cart.app.dto.ApiResponse;
import com.nisum.vibe.cart.app.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SendEmail sendEmailTest;
    @Value("${scm.order.service.url}")
    private String ORDER_SERVICE_URL;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderDTO.setOrderId("NA");
        OrderDTO createdOrder = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<OrderDTO> requestEntity = new HttpEntity<>(orderDTO, headers);
            ResponseEntity<ApiResponse<OrderDTO>> response = restTemplate.exchange(ORDER_SERVICE_URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<OrderDTO>>() {
            });
            ApiResponse<OrderDTO> apiResponse = response.getBody();
            createdOrder = apiResponse != null ? apiResponse.getData() : null;
            if (createdOrder != null) {
                String toEmail = createdOrder.getCustomer().getEmail();
                sendEmailTest.sendOrderConfirmationEmail(toEmail, createdOrder);
            } else {
                logger.warn("Order creation failed. No data returned from API.");
            }
        } catch (Exception e) {
            logger.error("An error occurred while creating the order: {}", e.getMessage());
        }

        return createdOrder;
    }
}
