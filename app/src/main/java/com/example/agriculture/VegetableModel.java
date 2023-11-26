package com.example.agriculture;

public class VegetableModel {

    private String Description;
    private String Name;
    private String Price;
    private String Quantity;
    private String UPIID;
    private String image;

        // Generate getters and setters for these fields.


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUPIID() {
        return UPIID;
    }

    public void setUPIID(String UPIID) {
        this.UPIID = UPIID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
