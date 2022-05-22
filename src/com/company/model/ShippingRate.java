package com.company.model;

/**
 * Contains values for the shiiping cost from RO,UK and US and returns that value
 */
public class ShippingRate {
    private static final double costforRO=1.0;
    private static final double costforUK=2.0;
    private static final double costforUS=3.0;

    public ShippingRate() {
    }

    public double getPriceforNation(String country){
        if(country.equals("RO"))
            return costforRO;
        if(country.equals("UK"))
            return costforUK;
        return costforUS;
    }
}
