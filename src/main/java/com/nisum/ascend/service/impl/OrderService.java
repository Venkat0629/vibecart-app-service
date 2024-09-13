package com.nisum.ascend.service.impl;

import com.nisum.ascend.dto.ApiResponse;
import com.nisum.ascend.dto.OrderDTO;
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
    @Value("${external.order.api.url}")
    private String EXTERNAL_ORDER_API_URL;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderDTO.setOrderId("NA");
        OrderDTO createdOrder = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<OrderDTO> requestEntity = new HttpEntity<>(orderDTO, headers);
            ResponseEntity<ApiResponse<OrderDTO>> response = restTemplate.exchange(EXTERNAL_ORDER_API_URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<OrderDTO>>() {
            });
            ApiResponse<OrderDTO> apiResponse = response.getBody();
            createdOrder = apiResponse != null ? apiResponse.getData() : null;
            if (createdOrder != null) {
                String toEmail = createdOrder.getCustomer().getEmail(); // Assuming getEmail() method exists
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
