package com.nisum.vibe.cart.app.dto;

import java.time.LocalDate;
import java.util.List;

public class CatalogDTO {

    private Long catalogID;

    private String catalogName;

    private String catalogDescription;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<CatalogItemsDTO> catalogItems;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String imageURL;

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

    public List<CatalogItemsDTO> getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(List<CatalogItemsDTO> catalogItems) {
        this.catalogItems = catalogItems;
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

    public CatalogDTO() {
    }

    public CatalogDTO(Long catalogID, String catalogName, String catalogDescription, LocalDate startDate, LocalDate endDate, List<CatalogItemsDTO> catalogItems, LocalDate createdDate, LocalDate updatedDate,String imageURL) {
        this.catalogID = catalogID;
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.catalogItems = catalogItems;
        this.imageURL=imageURL;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "CatalogDTO{" +
                "catalogID=" + catalogID +
                ", catalogName='" + catalogName + '\'' +
                ", catalogDescription='" + catalogDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", catalogItems=" + catalogItems +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
