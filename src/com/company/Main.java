package com.company;

import com.company.model.Product;
import com.company.model.ShippingRate;
import com.company.service.Service;
import com.company.ui.Ui;

public class Main {

    public static void main(String[] args) throws Exception {
        ShippingRate shippingRate=new ShippingRate();
        Service service=new Service();
        Ui ui=new Ui(service);
        ui.meniu();
    }
}
