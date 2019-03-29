package com.retail.store.discount.service;

import com.retail.store.discount.dto.RetailDiscountDto;
import com.retail.store.discount.dto.RetailRequestDto;
import com.retail.store.discount.dto.UserDto;
import com.retail.store.discount.entity.User;
import com.retail.store.discount.enumeration.UserType;
import com.retail.store.discount.repository.UserRepository;
import com.retail.store.discount.util.NumberUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RetailStoreService {

    private UserRepository userRepository;

    public RetailStoreService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RetailDiscountDto getRetailDiscount(RetailRequestDto retailRequestDto){
        // get user details
        User user = userRepository.findOneById(retailRequestDto.getUserId());
        if(user == null){
            // TODO : throw exception
        }

        double discountAmount = getDiscountAmount(retailRequestDto.getAmount(), user);
        double netPayableAmount = retailRequestDto.getAmount() - discountAmount;

        // TODO : use map struct
        return new RetailDiscountDto()
                .setUser(new UserDto().setId(user.getId()).setUserType(user.getUserType()).setName(user.getName()).setStartDate(user.getStartDate()))
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
        double discountPercentage = userType.getDiscount();
        if(userType.equals(UserType.CUSTOMER)){
            long numberOfYears = ChronoUnit.YEARS.between(LocalDate.now(), startDate);
            if (numberOfYears >= 2) {
                discountPercentage = 5.0;
            }
        }
        return discountPercentage;
    }

}
