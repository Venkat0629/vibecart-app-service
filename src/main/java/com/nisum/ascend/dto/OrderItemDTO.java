package com.nisum.ascend.dto;


import java.math.BigDecimal;


public class OrderItemDTO {
    private Long orderItemId;
    private Long itemId;
    private Long skuId;
    private String itemName;
    private String category;
    private String selectedSize;
    private String selectedColor;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long orderItemId, Long itemId, Long skuId, String itemName, String category, String selectedSize, String selectedColor, int quantity, BigDecimal price, BigDecimal totalPrice, BigDecimal discountPrice) {
        this.orderItemId = orderItemId;
        this.itemId = itemId;
        this.skuId = skuId;
        this.itemName = itemName;
        this.category = category;
        this.selectedSize = selectedSize;
        this.selectedColor = selectedColor;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
