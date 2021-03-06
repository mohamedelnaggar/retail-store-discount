package com.retail.store.discount.dto;

import com.retail.store.discount.entity.User;

import java.io.Serializable;

public class RetailResponseDto implements Serializable {

    private User user;
    private Double originalAmount;
    private Double discountAmount;
    private Double netPayableAmount;

    public User getUser() {
        return user;
    }

    public RetailResponseDto setUser(User user) {
        this.user = user;
        return this;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public RetailResponseDto setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
        return this;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public RetailResponseDto setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public Double getNetPayableAmount() {
        return netPayableAmount;
    }

    public RetailResponseDto setNetPayableAmount(Double netPayableAmount) {
        this.netPayableAmount = netPayableAmount;
        return this;
    }

    @Override
    public String toString() {
        return "RetailResponseDto{" +
                "user=" + user +
                ", originalAmount=" + originalAmount +
                ", discountAmount=" + discountAmount +
                ", netPayableAmount=" + netPayableAmount +
                '}';
    }
}
