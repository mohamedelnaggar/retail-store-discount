package com.retail.store.discount.enumeration;

public enum UserType{

    EMPLOYEE(30.0),
    AFFILIATE(10.0),
    CUSTOMER(0.0),
    GROCERIES(0.0);


    private double discountPercentage;

    UserType(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
