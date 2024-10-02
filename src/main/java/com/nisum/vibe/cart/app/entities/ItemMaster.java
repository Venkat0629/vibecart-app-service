package com.nisum.vibe.cart.app.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ItemMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemID;

    private String itemName;

    private String itemDescription;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Double price;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    //field related to ItemVariant, introduced to differentiate Items with different sizes and colors
    @OneToMany(mappedBy = "itemMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVariant> itemVariants;

    public List<ItemVariant> getItemVariants() {
        return itemVariants;
    }

    public void setItemVariants(List<ItemVariant> itemVariants) {
        this.itemVariants = itemVariants;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public ItemMaster() {
    }

    public ItemMaster(Long itemID, String itemName, String itemDescription, Category category, Double price, List<ItemVariant> itemVariants, LocalDate createdDate, LocalDate updatedDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.price = price;
        this.itemVariants = itemVariants;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "ItemMaster{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", itemVariants=" + itemVariants +
                '}';
    }
}
