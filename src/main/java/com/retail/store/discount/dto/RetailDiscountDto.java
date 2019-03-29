package com.retail.store.discount.dto;

import java.io.Serializable;

public class RetailDiscountDto implements Serializable {

    private UserDto user;
    private Double originalAmount;
    private Double discountAmount;
    private Double netPayableAmount;

    public UserDto getUser() {
        return user;
    }

    public RetailDiscountDto setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public RetailDiscountDto setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
        return this;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public RetailDiscountDto setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public Double getNetPayableAmount() {
        return netPayableAmount;
    }

    public RetailDiscountDto setNetPayableAmount(Double netPayableAmount) {
        this.netPayableAmount = netPayableAmount;
        return this;
    }

    @Override
    public String toString() {
        return "RetailDiscountDto{" +
                "user=" + user +
                ", originalAmount=" + originalAmount +
                ", discountAmount=" + discountAmount +
                ", netPayableAmount=" + netPayableAmount +
                '}';
    }
}
