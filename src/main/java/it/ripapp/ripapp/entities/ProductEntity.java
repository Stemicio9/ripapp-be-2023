package it.ripapp.ripapp.entities;

import java.util.UUID;

public class ProductEntity implements IEntity{

    private UUID productId;

    private String productName;

    private double price;

    private String urlImage;


    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
