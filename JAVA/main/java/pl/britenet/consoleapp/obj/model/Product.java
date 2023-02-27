package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public final class Product {

    private final int id;

    private String name;
    private String description;
    private Double price;

    private String image;

    public Product(int id) {
        this.id = id;
    }

    public Product() {
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
