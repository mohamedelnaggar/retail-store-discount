package com.retail.store.discount.dto;

import java.io.Serializable;

public class RetailRequestDto implements Serializable {

    private Double amount;
    private Long userId;

    public Double getAmount() {
        return amount;
    }

    public RetailRequestDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RetailRequestDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "RetailRequestDto{" +
                "amount=" + amount +
                ", userId=" + userId +
                '}';
    }
}
