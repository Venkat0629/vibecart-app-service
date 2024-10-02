package com.nisum.vibe.cart.app.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private Long catalogID;

    private String catalogName;

    private String catalogDescription;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    @OneToMany(mappedBy = "catalog",cascade = CascadeType.REMOVE)
    private List<CatalogItems> catalogItems;

    private  String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(Long catalogID) {
        this.catalogID = catalogID;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public List<CatalogItems> getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(List<CatalogItems> catalogItems) {
        this.catalogItems = catalogItems;
    }

    public Catalog() {
    }

    public Catalog(Long catalogID, String catalogName, String catalogDescription, LocalDate startDate, LocalDate endDate, LocalDate createdDate, LocalDate updatedDate, List<CatalogItems> catalogItems,String imageURL) {
        this.catalogID = catalogID;
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.catalogItems = catalogItems;
        this.imageURL=imageURL;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogID=" + catalogID +
                ", catalogName='" + catalogName + '\'' +
                ", catalogDescription='" + catalogDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", catalogItems=" + catalogItems +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
