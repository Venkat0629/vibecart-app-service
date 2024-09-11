package com.nisum.ascend.dto;

import java.util.List;

public class SkuQuantityResponse {

    private Long skuID;
    private Integer totalQuantity;

    public SkuQuantityResponse(List<Integer> data) {
    }

    public SkuQuantityResponse(Long skuID, Integer totalQuantity) {
        this.skuID = skuID;
        this.totalQuantity = totalQuantity;
    }

    public Long getSkuID() {
        return skuID;
    }

    public void setSkuID(Long skuID) {
        this.skuID = skuID;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
 