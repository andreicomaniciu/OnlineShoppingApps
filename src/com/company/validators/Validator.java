package com.company.validators;

public interface Validator<E> {
    void validate(E entity) throws ValidatorException;
}