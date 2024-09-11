package com.nisum.ascend.dto;

import java.time.LocalDate;
import java.util.List;

public class CategoryDTO {

    private Long categoryID;

    private String categoryName;

    private String description;

    private Long parentCategoryID;

    private List<CategoryDTO> subCategories;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(Long parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public List<CategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryDTO> subCategories) {
        this.subCategories = subCategories;
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

    public CategoryDTO() {
    }

    public CategoryDTO(Long categoryID, String categoryName, String description, Long parentCategoryID, List<CategoryDTO> subCategories, LocalDate createdDate, LocalDate updatedDate) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategoryID = parentCategoryID;
        this.subCategories = subCategories;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", parentCategoryID=" + parentCategoryID +
                ", subCategories=" + subCategories +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
