package com.kakaotalk.gift.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private Integer status;
    private String message;
    private List<FieldErrors> errors;

    public ErrorResponse(ResponseStatus status, String message, List<FieldErrors> errors) {
        this.status = status.getStatus();
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(ResponseStatus status, String message, BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<FieldErrors> fieldErrors = errors.stream()
                .map(FieldErrors::new)
                .collect(Collectors.toList());

        return new ErrorResponse(status, message, fieldErrors);
    }

    public static ErrorResponse of(ResponseStatus status, String message) {
        return new ErrorResponse(status, message, null);
    }

    @Getter
    public static class FieldErrors {

        private String field;
        private String value;
        private String reason;

        public FieldErrors(FieldError fieldError) {
            this.field = fieldError.getField();
            this.value = String.valueOf(fieldError.getRejectedValue());
            this.reason = fieldError.getDefaultMessage();
        }
    }
}