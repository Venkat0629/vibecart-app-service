package com.nisum.ascend.dto;

import java.util.List;

public class ItemVariantDTO {

    private Long skuID;
    private Color color;
    private Size size;
    private Long itemID;

    //fields from itemMaster;
    private String itemName;
    private String itemDescription;
    private Double price;
    private List<String> imageURLs;
    private String imageURL;
    private String categoryName;
    private List<String> availableColors;
    private List<String> availableSizes;

    private String parentCategoryName; //for inventory

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<String> availableColors) {
        this.availableColors = availableColors;
    }

    public List<String> getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(List<String> availableSizes) {
        this.availableSizes = availableSizes;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getSkuID() {
        return skuID;
    }

    public void setSkuID(Long skuID) {
        this.skuID = skuID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public ItemVariantDTO() {
    }

    public ItemVariantDTO(Long skuID, Color color, Size size, Long itemID, String imageURL, String itemName, String itemDescription, Double price, List<String> imageURLs, String categoryName, String parentCategoryName, List<String> availableColors, List<String> availableSizes) {
        this.skuID = skuID;
        this.color = color;
        this.size = size;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.imageURLs = imageURLs;
        this.categoryName = categoryName;
        this.parentCategoryName=parentCategoryName;
        this.availableColors = availableColors;
        this.availableSizes = availableSizes;
        this.imageURL=imageURL;
    }

    @Override
    public String toString() {
        return "ItemVariantDTO{" +
                "skuID=" + skuID +
                ", color=" + color +
                ", size=" + size +
                ", itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", price=" + price +
                ", imageURLs=" + imageURLs +
                ", imageURL='" + imageURL + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryName='" + parentCategoryName + '\'' +
                ", availableColors=" + availableColors +
                ", availableSizes=" + availableSizes +
                '}';
    }
}
