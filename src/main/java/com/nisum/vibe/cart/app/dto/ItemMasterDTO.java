package com.nisum.vibe.cart.app.dto;

import java.time.LocalDate;
import java.util.List;

public class ItemMasterDTO {

    private Long itemID;

    private String itemName;

    private String itemDescription;

    private Long categoryID;

    private String categoryName;

    private String parentCategoryName;

    private Double price;

    private List<String> imageURLs;

    private List<String> availableColors;

    private List<String> availableSizes;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(List<String> availableSizes) {
        this.availableSizes = availableSizes;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<String> availableColors) {
        this.availableColors = availableColors;
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

    public ItemMasterDTO() {
    }

    public ItemMasterDTO(Long itemID,  String itemName, String parentCategoryName, String itemDescription, Long categoryID, Double price, List<String> imageURLs, List<String> availableColors, String categoryName, List<String> availableSizes, LocalDate createdDate, LocalDate updatedDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.categoryID = categoryID;
        this.price = price;
        this.imageURLs = imageURLs;
        this.availableColors = availableColors;
        this.availableSizes = availableSizes;
        this.categoryName=categoryName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.parentCategoryName=parentCategoryName; //for inventory service
    }

    public ItemMasterDTO(Long itemID, String itemName, String itemDescription, Long categoryID, Double price, LocalDate createdDate, LocalDate updatedDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.categoryID = categoryID;
        this.price = price;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "ItemMasterDTO{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", imageURLs=" + imageURLs +
                ", availableColors=" + availableColors +
                ", availableSizes=" + availableSizes +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
