package com.retail.store.discount.exception;

import java.util.Map;

public class ApiError {

    private final String errorNumber;
    private final String errorMessage;
    private final Map<String, String> detailedErrors;

    public ApiError(String errorNumber, String message, Map<String, String> detailedErrors) {
        this.errorNumber = errorNumber;
        this.errorMessage = message;
        this.detailedErrors = detailedErrors;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, String> getDetailedErrors() {
        return detailedErrors;
    }
}
