package com.company.validators;

public class ProductInputValidator implements Validator<String>{
    /**
     * Vlidates if the given string mathces a product in the catalog
     * @param entity:String
     * @throws ValidatorException
     */
    @Override
    public void validate(String entity) throws ValidatorException {
        if(!entity.equals("Mouse") &&
        !entity.equals("Keyboard") &&
        !entity.equals("Monitor") &&
        !entity.equals("Webcam") &&
        !entity.equals("Headphones") &&
        !entity.equals("Desk Lamp")){
            throw new ValidatorException("Item has to be in the catalog!");
        }
    }
}
