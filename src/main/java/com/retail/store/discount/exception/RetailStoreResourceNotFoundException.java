package com.retail.store.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RetailStoreResourceNotFoundException extends RuntimeException {

    private static final String errorNumber = "RETAIL-STORE-ERROR-001";

    private Resource resourceType;
    private Long resourceId;

    public enum Resource {

        USER("user");

        private String value;

        Resource(String value) {
            this.value = value;
        }

        public String toString() {
            return value.toLowerCase();
        }
    }

    public RetailStoreResourceNotFoundException(Resource resourceType, Long resourceId) {
        super(String.format("%s '%s' couldn't be found.", resourceType, resourceId));
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public Resource getResourceType() {
        return resourceType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public String getErrorNumber() {
        return errorNumber;
    }
}

