package com.company.service;

import com.company.model.Invoice;
import com.company.model.Order;
import com.company.model.Product;
import com.company.repository.CatalogRepository;
import com.company.validators.ProductInputValidator;
import com.company.validators.ValidatorException;


public class Service {
    private Order order;
    private CatalogRepository catalogRepository;
    private ProductInputValidator productInputValidator;

    public CatalogRepository getCatalogRepository() {
        return catalogRepository;
    }

    /**
     * Constructor
     */
    public Service() {
        this.order = new Order();
        this.catalogRepository = new CatalogRepository();
        this.productInputValidator = new ProductInputValidator();
    }

    public Order getOrder() {
        return order;
    }

    public void AddProductToCatalog(Product product){
        catalogRepository.save(product);
    }

    public double getInvoice(){
        return order.getInvoice().getTotal();
    }

    /**
     * Adds a product to the order
     * Validates the product name and adds it to the order returning a proper message
     * @param productName: String
     * @return  error message if the product name is not correct, or Done otherwise
     */
    public String AddOrder(String productName){
        try{
            productInputValidator.validate(productName);
            order.addOrder(productName);
        }catch (ValidatorException ex){
            return ex.getMessage();
        }
        return "Done";
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Creates the invoice for the program
     * Reads products and quantity from the order list and composes the invoice parameters
     * checks for keyboard discount, 2 monitors and one desk lamp at half price discount, 2 or more items
     * shipping discount, and applies all of them to the invoice discount section
     * Finally, it sets the created invoice to the current order invoice
     */
    public void ComposeInvoice(){
        Invoice invoice = new Invoice();
        for(var x:order.getOrder().keySet()){
            Product product=catalogRepository.getProductByName(x);
            Integer counter=order.getOrder().get(x);
            invoice.setProductsTotal(invoice.getProductsTotal()+counter*product.getPrice());
            invoice.setShipping(invoice.getShipping()+counter*product.getShippingCost());
            invoice.setVAT(invoice.getVAT()+counter*product.getVAT());
        }

        //10% off times each keyboard in the order
        if(0<checkForKeyboardsDiscount(order)){
            invoice.setDiscounts(invoice.getDiscounts()+
                    (checkForKeyboardsDiscount(order)*( (10 * catalogRepository.getProductByName("Keyboard").getPrice() )/100)));
        }

        //Desk lamp at half price
        if(checkFor2MonitorsDiscount(order)){
            invoice.setDiscounts(invoice.getDiscounts() +
                    (catalogRepository.getProductByName("Desk Lamp").getPrice() / 2));
        }

        //10$ off shipping
        if(checkFor2OrMoreItemsDiscount(order)){
            invoice.setDiscounts( invoice.getDiscounts()+ 10.0);
        }
        invoice.setTotal(invoice.getProductsTotal() + invoice.getShipping()
                + invoice.getVAT() - invoice.getDiscounts());

        order.setInvoice(invoice);
    }

    /**
     * Checks how many keyboards are in the order
     * @param order :Order
     * @return number of keyboards in the order
     */
    private int checkForKeyboardsDiscount(Order order){
        if(order.getOrder().get("Keyboard")!=null)
            return order.getOrder().get("Keyboard");
        else
            return 0;
    }

    /**
     *  Checks if the 2 monitors and one desk lamp at half price discount can be applied
     * @param order:Order
     * @return true if the order contains 2 monitors and one desk lamp, false otherwise
     */
    private boolean checkFor2MonitorsDiscount(Order order){
        if(order.getOrder().containsKey("Monitor") && order.getOrder().get("Monitor") == 2 && order.getOrder().containsKey("Desk Lamp")){
            return true;
        }
        return false;
    }

    /**
     * Checks if the 10$ shipping discount can be applied
     * @param order:Order
     * @return true if there are at least 2 products in the order, false otherwise
     */
    private boolean checkFor2OrMoreItemsDiscount(Order order){
        int count=0;
        for(var x: order.getOrder().values())
        {
            count+=x;
        }
        if(count>=2){
            return true;
        }
        return false;
    }
}
