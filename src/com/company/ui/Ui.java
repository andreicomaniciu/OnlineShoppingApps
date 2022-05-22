package com.company.ui;


import com.company.model.Order;
import com.company.model.Product;
import com.company.model.ShippingRate;
import com.company.service.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Service service;

    private Scanner scan = new Scanner(System.in);


    public Ui(Service service) {
        this.service = service;
        populateCatalog(service);
    }

    /**
     * Populates the catalog
     * adds products to the product catalog and sets their shipping cost
     * @param service:Service
     */
    private void populateCatalog(Service service){
        ShippingRate shippingRate=new ShippingRate();
        Product product=new Product("Mouse",10.99,"RO", 0.2);
        Product product1=new Product("Keyboard",40.99,"UK", 0.7);
        Product product2=new Product("Monitor",164.99,"US", 1.9);
        Product product3=new Product("Webcam",84.99,"RO", 0.2);
        Product product4=new Product("Headphones",59.99,"US", 0.6);
        Product product5=new Product("Desk Lamp",89.99,"UK", 1.3);

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
    }

    /**
     * Prints the option of the meniu
     * @return String
     */
    private String optiuni() {
        ArrayList<String> optiuni = new ArrayList<>();
        optiuni.add(new String("1. Show current cart - Press 1"));
        optiuni.add(new String("2. Add product to cart - Press 2"));
        optiuni.add(new String("3. Get Total - Press 3"));
        optiuni.add(new String("4. New Cart - Press 4"));
        optiuni.add(new String("0. Exit - Press 0"));

        String meniu = new String("");

        for (var x : optiuni) {
            meniu = meniu + x + "\n";
        }

        return meniu;
    }

    /**
     * prints the current catalog created
     */
    public void  showCatalog(){
        System.out.println("Current product catalog:");
        for(var x:service.getCatalogRepository().getAll()){
            System.out.println(x.getItemName()+" - $"+x.getPrice());
        }
        System.out.println("\n");
    }

    /**
     * Prints the current cart
     */
    public void showCart(){
        System.out.println("Current Cart:\n");
        for(var x: service.getOrder().getOrder().keySet()){
            System.out.println(x+" x "+service.getOrder().getOrder().get(x));
        }
    }

    /**
     * Adds a product to the order
     */
    public void addOrder(){
        System.out.println("Product: ");
        System.out.println(service.AddOrder(scan.nextLine())+"\n");
    }

    /**
     * Prints the current order invoice
     */
    public void getInvoice(){
        service.ComposeInvoice();
        if(service.getInvoice()>0.0){

            System.out.println("Subtotal: $"+String.format("%.2f",service.getOrder().getInvoice().getProductsTotal()));
            System.out.println("Shipping: $"+String.format("%.2f",service.getOrder().getInvoice().getShipping()));
            System.out.println("VAT: $"+String.format("%.2f",service.getOrder().getInvoice().getVAT()));
            System.out.println("Discount: $"+String.format("%.2f",service.getOrder().getInvoice().getDiscounts()));
            System.out.println("Your Total: ");
            System.out.format("%.2f",service.getInvoice());
            System.out.println("\n");
        }
        else
            System.out.println("No product in current cart!");


    }

    /**
     * deletes the current cart and creates a new order
     */
    public void newCart(){
        Order order=new Order();
        service.setOrder(order);
    }

    public void meniu() throws Exception {
        String s = "";
        showCatalog();
        while (!s.equals("0")) {

            System.out.println("Options:");
            System.out.println(optiuni());
            System.out.println("Type your option: ");

            s = scan.nextLine();

            switch (s) {
                case "1":
                    showCart();
                    break;
                case "2":
                    addOrder();
                    break;
                case "3":
                    getInvoice();
                    break;
                case "4":
                    newCart();
                    break;
                case "0":
                    System.out.println("Multumin pentru utilizarea aplicatiei!");
                    break;
                default:
                    System.out.println("Optiunea data nu este valida!\n");
                    break;
            }
        }
    }


}
