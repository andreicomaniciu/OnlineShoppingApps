package com.company.service;

import com.company.model.Product;
import com.company.model.ShippingRate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Service methods.
 */
public class ServiceTest {
    Service service=new Service();
    ShippingRate shippingRate=new ShippingRate();
    Product product=new Product("Mouse",10.99,"RO", 0.2);
    Product product1=new Product("Keyboard",40.99,"UK", 0.7);
    Product product2=new Product("Monitor",164.99,"US", 1.9);
    Product product3=new Product("Webcam",84.99,"RO", 0.2);
    Product product4=new Product("Headphones",59.99,"US", 0.6);
    Product product5=new Product("Desk Lamp",89.99,"UK", 1.3);

    /**
     * Test if the products are added to the product catalog.
     */
    @Test
    public void addProductToCatalog() {
        service.AddProductToCatalog(product);
        service.AddProductToCatalog(product1);
        service.AddProductToCatalog(product2);
        service.AddProductToCatalog(product3);
        service.AddProductToCatalog(product4);
        service.AddProductToCatalog(product5);

        assertEquals(service.getCatalogRepository().getAll().size(),6);
    }

    /**
     * Test if the program returns an error message at wrong input
     */
    @Test
    public void addProductToCart() {
        service.AddProductToCatalog(product);
        service.AddProductToCatalog(product1);
        service.AddProductToCatalog(product2);
        service.AddProductToCatalog(product3);
        service.AddProductToCatalog(product4);
        service.AddProductToCatalog(product5);

        assertEquals("Done",service.AddOrder("Monitor"));
        assertEquals("Item has to be in the catalog!",service.AddOrder("Monitors"));
    }

    /**
     * Test if the invoice returns the correct value for 2 monitors and one desk lamp( in order to test the discount applied), for
     * 2 monitors and one keyboard as the given example and if the user order is empty.
     */
    @Test
    public void composeInvoice() {
        product.setShippingCost((product.getWeight()/0.1)*shippingRate.getPriceforNation(product.getCountry()));
        product1.setShippingCost((product1.getWeight()/0.1)*shippingRate.getPriceforNation(product1.getCountry()));
        product2.setShippingCost((product2.getWeight()/0.1)*shippingRate.getPriceforNation(product2.getCountry()));
        product3.setShippingCost((product3.getWeight()/0.1)*shippingRate.getPriceforNation(product3.getCountry()));
        product4.setShippingCost((product4.getWeight()/0.1)*shippingRate.getPriceforNation(product4.getCountry()));
        product5.setShippingCost((product5.getWeight()/0.1)*shippingRate.getPriceforNation(product5.getCountry()));


        service.AddProductToCatalog(product);
        service.AddProductToCatalog(product1);
        service.AddProductToCatalog(product2);
        service.AddProductToCatalog(product3);
        service.AddProductToCatalog(product4);
        service.AddProductToCatalog(product5);

        service.AddOrder("Desk Lamp");
        service.AddOrder("Monitor");
        service.AddOrder("Monitor");
        service.ComposeInvoice();

        assertEquals(service.getInvoice(),584.76, 0.01);

        Service service1=new Service();
        service1.AddProductToCatalog(product);
        service1.AddProductToCatalog(product1);
        service1.AddProductToCatalog(product2);
        service1.AddProductToCatalog(product3);
        service1.AddProductToCatalog(product4);
        service1.AddProductToCatalog(product5);

        service1.AddOrder("Keyboard");
        service1.AddOrder("Monitor");
        service1.AddOrder("Monitor");
        service1.ComposeInvoice();

        assertEquals(service1.getInvoice(),555.36, 0.01);

        Service service2=new Service();
        service2.AddProductToCatalog(product);
        service2.AddProductToCatalog(product1);
        service2.AddProductToCatalog(product2);
        service2.AddProductToCatalog(product3);
        service2.AddProductToCatalog(product4);
        service2.AddProductToCatalog(product5);

        service2.ComposeInvoice();

        assertEquals(service2.getInvoice(),0.0, 0.01);

    }
}