package com.nisum.vibe.cart.app.entities;

import com.nisum.vibe.cart.app.dto.Color;
import com.nisum.vibe.cart.app.dto.Size;

import javax.persistence.*;

@Entity
public class ItemVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku_id")
    private Long skuID;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public ItemVariant() {
    }

    public ItemVariant(Long skuID, Color color, Size size, String imageURL, ItemMaster itemMaster) {
        this.skuID = skuID;
        this.color = color;
        this.size = size;
        this.itemMaster = itemMaster;
        this.imageURL=imageURL;
    }

    @Override
    public String toString() {
        return "ItemVariant{" +
                "skuID=" + skuID +
                ", color=" + color +
                ", size=" + size +
                ", itemMaster=" + itemMaster +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
