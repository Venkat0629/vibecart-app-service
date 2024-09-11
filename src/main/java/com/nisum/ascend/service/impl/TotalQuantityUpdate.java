package com.nisum.ascend.service.impl;

import com.nisum.ascend.dto.SkuQuantityResponse;
import com.nisum.ascend.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vibecart/ecom/sku")
public class TotalQuantityUpdate {

    private final RestTemplate restTemplate;

    @Autowired
    public TotalQuantityUpdate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/total-quantity")
    public ResponseEntity<List<SkuQuantityResponse>> checkItemQuantity(@RequestBody List<Long> skuList) {
        String url = "http://localhost:5601/vibe-cart/scm/inventory/check-quantity";
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(skuList);
        ResponseEntity<ApiResponse<List<Integer>>> response = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<List<Integer>>>() {
                }
        );
        ApiResponse<List<Integer>> responseBody = response.getBody();
        List<SkuQuantityResponse> responseList = new ArrayList<>();
        for (int i = 0; i < skuList.size(); i++) {
            responseList.add(new SkuQuantityResponse(skuList.get(i), responseBody.getData().get(i)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}