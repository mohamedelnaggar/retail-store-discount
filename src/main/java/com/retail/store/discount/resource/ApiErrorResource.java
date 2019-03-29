package com.retail.store.discount.resource;


import com.retail.store.discount.exception.ApiError;
import com.retail.store.discount.exception.ExceptionHandlingController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ApiErrorResource extends ResourceSupport {

    private final ApiError apiError;

    public ApiErrorResource(ApiError apiError) {
        this.apiError = apiError;
        String errorNumber = apiError.getErrorNumber();
        this.add(linkTo(methodOn(ExceptionHandlingController.class, errorNumber).getError(errorNumber)).withSelfRel());
    }

    public ApiError getApiError() {
        return apiError;
    }
}
