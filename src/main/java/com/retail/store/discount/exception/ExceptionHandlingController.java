package com.retail.store.discount.exception;

import com.retail.store.discount.resource.ApiErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(RetailStoreResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResource> notFoundException(final RetailStoreResourceNotFoundException e) {
        log.error(String.format("Error: %s / %s", e.getErrorNumber(), e.getMessage()));
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getResourceType().toString(), e.getMessage());
        ApiError apiError = new ApiError(e.getErrorNumber(), e.getMessage(), errors);
        return new ResponseEntity<>(new ApiErrorResource(apiError), HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/errors-directory/{errorReference}")
    public ResponseEntity getError(@PathVariable("errorReference") String errorReference){
        return ResponseEntity.notFound().build();
    }
}
