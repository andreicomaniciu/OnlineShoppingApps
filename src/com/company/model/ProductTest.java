package com.company.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    Product product=new Product("Mouse",10.99,"UK",0.2);


    @Test
    public void getItemName() {
        assertEquals(product.getItemName(),"Mouse");
    }

    @Test
    public void setItemName() {
        product.setItemName("Mouse2");
        assertEquals(product.getItemName(),"Mouse2");
    }

    @Test
    public void getPrice() {
        assertEquals(product.getPrice(),10.99,0.1);
    }

    @Test
    public void setPrice() {
        product.setPrice(11.00);
        assertEquals(product.getPrice(),11.00,0.1);
    }

    @Test
    public void getCountry() {
        assertEquals(product.getCountry(),"RO");
    }

    @Test
    public void setCountry() {
        product.setCountry("UK");
        assertEquals(product.getCountry(),"UK");
    }

    @Test
    public void getWeight() {
        assertEquals(product.getWeight(),0.2,0.1);
    }

    @Test
    public void setShippingCost() {
        ShippingRate shippingRate=new ShippingRate();
        product.setShippingCost((product.getWeight()/0.1)*shippingRate.getPriceforNation(product.getCountry()));
        assertEquals(product.getShippingCost(),4.0,0.01);
    }

    @Test
    public void getVAT() {
        assertEquals(product.getVAT(),2.08,0.01);
    }
}