package com.company.model;

/**
 * Invoice class consists of all the components that are necessary for composing the invoice
 */
public class Invoice {
    private double productsTotal;
    private double shipping;
    private double total;
    private double VAT;
    private double discounts;

    /**
     * constructor
     */
    public Invoice() {
        this.productsTotal = 0.0;
        this.shipping = 0.0;
        this.total = 0.0;
        this.VAT = 0.0;
        this.discounts = 0.0;
    }

    public double getProductsTotal() {
        return productsTotal;
    }

    public void setProductsTotal(double productsTotal) {
        this.productsTotal = productsTotal;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }
}
