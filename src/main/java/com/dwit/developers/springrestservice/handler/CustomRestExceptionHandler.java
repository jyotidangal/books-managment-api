package com.dwit.developers.springrestservice.handler;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.controller.BaseController;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.exception.PageOutOfRangeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * RestControllerAdvice == for REST APIs, common logic for all controller
 * It supports Write once use anytime concepts.
 * is global exception handler that is combination of
 *  -- @ControllerAdvice === view based responses (Thymeleaf/ JSP)&
 *  -- @ResponseBody === returns the  JSON data
 */
@Slf4j
@RestControllerAdvice
public class CustomRestExceptionHandler extends BaseController {
    public final CustomMessageSource customMessageSource;
    public CustomRestExceptionHandler(CustomMessageSource customMessageSource) {
        this.customMessageSource = customMessageSource;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
            errors.put(error.getField(),
                    error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
    // Data integrity violation (Unique Key / Foreign Key constraints)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMsg;

        // Try to get the underlying constraint name
        Throwable cause = ex.getCause();
        if (cause instanceof org.hibernate.exception.ConstraintViolationException cve) {
            String constraintName = cve.getConstraintName(); // e.g., uk_warehouse_name
            if (constraintName != null) {
                String[] parts = constraintName.split("_", 3); // [uk, warehouse, name]
                String constraintType = parts[0]; // "uk" or "fk"
                String keyName = parts.length > 2 ? parts[1] + "_" + parts[2] : parts[1]; // "warehouse_name"

                // Try to get friendly field name, fallback to raw keyName
                String displayName;
                try {
                    displayName = customMessageSource.get(keyName);
                } catch (Exception e) {
                    displayName = keyName;
                }

                // Handle unique key violation
                if ("uk".equalsIgnoreCase(constraintType)) {
                    errorMsg = customMessageSource.get(ResponseMessageConstant.ALREADY_EXISTS, displayName);
                    return ResponseEntity.badRequest().body(failureResponse(errorMsg, null));
                }

                // Handle foreign key violation
                if ("fk".equalsIgnoreCase(constraintType)) {
                    errorMsg = "Foreign key constraint violation: " + displayName;
                    return ResponseEntity.badRequest().body(failureResponse(errorMsg, null));
                }
            }
        }

        // Fallback generic message
        errorMsg = "Data integrity violation";
        return ResponseEntity.badRequest().body(failureResponse(errorMsg, null));
    }
    @ExceptionHandler(PageOutOfRangeException.class)
    public ResponseEntity<ResponseDto> handlePageOutOfRange(PageOutOfRangeException ex) {
        return ResponseEntity.badRequest().body(failureResponse(ex.getMessage(), null));
    }



    //To Handle the exceptional case where there is data not send from the api
    @ExceptionHandler(ConfigDataNotFoundException.class)
    public ResponseEntity<ResponseDto> handleConfigDataNotFoundException(ConfigDataNotFoundException ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(failureResponse(customMessageSource.get(ResponseMessageConstant.VALIDATE_FAILURE, customMessageSource.get(EntityConstant.WAREHOUSE)),customMessageSource.get(ResponseMessageConstant.NULL)));
    }
    // To return the runtime exceptional errors to json data format in API
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(failureResponse(customMessageSource.get(ResponseMessageConstant.VALIDATE_FAILURE),null));
    }
    // The Parent class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(failureResponse(customMessageSource.get(ResponseMessageConstant.VALIDATE_FAILURE),null));
    }


}

