package com.retail.store.discount.service;

import com.retail.store.discount.dto.RetailRequestDto;
import com.retail.store.discount.dto.RetailResponseDto;
import com.retail.store.discount.entity.User;
import com.retail.store.discount.enumeration.UserType;
import com.retail.store.discount.util.NumberUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RetailStoreService {

    private final UserService userService;

    public RetailStoreService(UserService userService) {
        this.userService = userService;
    }

    public RetailResponseDto getRetailDiscount(RetailRequestDto retailRequestDto){
        User user = userService.getUserById(retailRequestDto.getUserId());

        double discountAmount = getDiscountAmount(retailRequestDto.getAmount(), user);
        double netPayableAmount = retailRequestDto.getAmount() - discountAmount;

        return new RetailResponseDto()
                .setUser(user)
                .setOriginalAmount(retailRequestDto.getAmount())
                .setDiscountAmount(discountAmount)
                .setNetPayableAmount(netPayableAmount);
    }

    private double getDiscountAmount(double amount, User user){
        double discountPercentageBasedOnUserType = getDiscountPercentageBasedOnUserType(user.getUserType(), user.getStartDate());
        double discountAmountBasedOnUserType = NumberUtils.calculatePercentageValue(amount, discountPercentageBasedOnUserType);


        int discountPerTotalAmountFactor = (int) amount / 100;
        double discountPerTotalAmount = discountPerTotalAmountFactor * 5.0;

        return Math.max(discountAmountBasedOnUserType, discountPerTotalAmount);
    }

    private double getDiscountPercentageBasedOnUserType(UserType userType, LocalDate startDate){
        if(userType == null){
            return 0.0;
        }
        double discountPercentage = userType.getDiscountPercentage();
        if(userType.equals(UserType.CUSTOMER)){
            long numberOfYears = ChronoUnit.YEARS.between(startDate, LocalDate.now());
            if (numberOfYears >= 2) {
                discountPercentage = 5.0;
            }
        }
        return discountPercentage;
    }

}
