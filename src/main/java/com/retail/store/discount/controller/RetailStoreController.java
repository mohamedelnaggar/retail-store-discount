package com.retail.store.discount.controller;


import com.retail.store.discount.dto.RetailRequestDto;
import com.retail.store.discount.service.RetailStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RetailStoreController {

    private final static Logger log = LoggerFactory.getLogger(RetailStoreController.class);

    private final RetailStoreService retailStoreService;

    public RetailStoreController(RetailStoreService retailStoreService) {
        this.retailStoreService = retailStoreService;
    }

    @PostMapping("/retail-store/calculate-discount")
    public ResponseEntity getRetailDiscount(@Valid @RequestBody RetailRequestDto retailRequestDto){
        log.debug("REST request to calculate amount for retail {}", retailRequestDto);
        return ResponseEntity.ok(retailStoreService.getRetailDiscount(retailRequestDto));
    }


}
