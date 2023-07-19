package com.kakaotalk.gift.global.exception;


import com.kakaotalk.gift.global.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kakaotalk.gift.global.response.ResponseStatus.CODE_400;
import static com.kakaotalk.gift.global.response.ResponseStatus.CODE_500;
import static com.kakaotalk.gift.global.util.MessageSourceUtils.getMessage;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(BindingResult bindingResult) {
        return new ResponseEntity<>(ErrorResponse.of(CODE_400, getMessage(CODE_400), bindingResult), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(BusinessException e) {
        return new ResponseEntity<>(ErrorResponse.of(CODE_500, getMessage(e.getErrorCode())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomIllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomIllegalArgumentException e) {
        return new ResponseEntity<>(ErrorResponse.of(CODE_400, getMessage(e.getErrorCode(), e.getMessages())), HttpStatus.BAD_REQUEST);
    }
}