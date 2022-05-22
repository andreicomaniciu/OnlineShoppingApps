package com.company.model;

/**
 * Product class contains all the components given for a product such as name,price, country and weight,
 * and it composes a value for VAT
 */
public class Product {
    private String itemName;
    private double price;
    private String country;
    private double weight;

    private double shippingCost;
    private double VAT;

    /**
     * Constructor
     * @param itemName
     * @param price
     * @param country
     * @param weight
     */
    public Product(String itemName, double price, String country, double weight) {
        this.itemName = itemName;
        this.price = price;
        this.country = country;
        this.weight = weight;

        this.VAT=(double) (19.0*this.price) / 100;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }
}
