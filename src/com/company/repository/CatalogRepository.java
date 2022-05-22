package com.company.repository;

import com.company.model.Product;

import java.util.*;

/**
 * Repository of products
 */
public class CatalogRepository {
    ArrayList<Product> products;


    public CatalogRepository() {
        products=new ArrayList<>();
    }

    public void save(Product product){
        products.add(product);
    }

    public Product getProductByName(String name){
        for(var x: products){
            if(x.getItemName().equals(name)){
                return x;
            }
        }
        return null;
    }

    public List<Product> getAll(){
        return products;
    }

}
