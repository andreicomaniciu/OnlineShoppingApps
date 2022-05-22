package com.company.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Order class consists of a map that contains all the products that are in the current Order, the key is the
 * product name and the value is the quantity of the product
 */
public class Order {
    private Map<String,Integer> order;
    private Invoice invoice;

    public Order() {
        this.order = new HashMap<String,Integer>();
        this.invoice=new Invoice();
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public Integer getOrderQuantity(String productName){
        return order.get(productName);
    }

    /**
     * Adds a product to the order
     * If the product already existed in the order, it increases its value by one, if not it adds a new
     * product to the order with the quantity = 1
     * @param productName, a valid product name that will be added in the map
     */
    public void addOrder(String productName){
        if(order.containsKey(productName))
            order.replace(productName,order.get(productName),order.get(productName)+1);
        else{
            order.put(productName,1);
        }
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
