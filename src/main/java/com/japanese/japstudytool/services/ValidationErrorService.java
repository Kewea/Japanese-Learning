package com.japanese.japstudytool.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorService {
    public Map<String, String> mapValidationError(BindingResult result) {
        Map<String, String> errorMap = new HashMap<>();

        if(result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        return errorMap;
    }
}
