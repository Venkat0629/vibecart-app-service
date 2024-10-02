package com.nisum.vibe.cart.app.dto;

import java.time.LocalDate;

public class CatalogItemsDTO {

    private Long catalogItemID;

    private Long catalogID;

    private Long itemID;

    private Double price;

    private Double discount;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    public Long getCatalogItemID() {
        return catalogItemID;
    }

    public void setCatalogItemID(Long catalogItemID) {
        this.catalogItemID = catalogItemID;
    }

    public Long getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(Long catalogID) {
        this.catalogID = catalogID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public CatalogItemsDTO() {
    }

    public CatalogItemsDTO(Long catalogItemID, Long catalogID, Long itemID, Double price, Double discount, LocalDate createdDate, LocalDate updatedDate) {
        this.catalogItemID = catalogItemID;
        this.catalogID = catalogID;
        this.itemID = itemID;
        this.price = price;
        this.discount = discount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "CatalogItemsDTO{" +
                "catalogItemID=" + catalogItemID +
                ", catalogID=" + catalogID +
                ", itemID=" + itemID +
                ", price=" + price +
                ", discount=" + discount +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
