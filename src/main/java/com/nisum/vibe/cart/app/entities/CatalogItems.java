package com.nisum.vibe.cart.app.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CatalogItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_item_id")
    private Long catalogItemID;

    @ManyToOne
    @JoinColumn(name = "catalog_id", nullable = false)
    private Catalog catalog;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemMaster item;

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

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ItemMaster getItem() {
        return item;
    }

    public void setItem(ItemMaster item) {
        this.item = item;
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

    public CatalogItems() {
    }

    public CatalogItems(Long catalogItemID, Catalog catalog, ItemMaster item, Double price, Double discount, LocalDate createdDate, LocalDate updatedDate) {
        this.catalogItemID = catalogItemID;
        this.catalog = catalog;
        this.item = item;
        this.price = price;
        this.discount = discount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "CatalogItems{" +
                "catalogItemID=" + catalogItemID +
                ", catalog=" + catalog +
                ", item=" + item +
                ", price=" + price +
                ", discount=" + discount +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
