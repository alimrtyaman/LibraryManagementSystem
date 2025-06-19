package com.aliyaman.libraryapp.handler;

import com.aliyaman.libraryapp.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError> handleBaseException(BaseException exception , WebRequest request){
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage() , request));
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleNotValidationException(MethodArgumentNotValidException ex , WebRequest request){
        Map<String , List<String>> errosMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message   = error.getDefaultMessage();
            errosMap
                    .computeIfAbsent(fieldName, key -> new ArrayList<>())
                    .add(message);
        });

        ApiError apiError = createApiError(errosMap, request);
        return ResponseEntity.badRequest().body(apiError);

    }


    public <E> ApiError<E> createApiError(E message , WebRequest request){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false).substring(4));
        exception.setHostName(getHostName());
        exception.setMessage(message);
        exception.setCreateTime(new Date());

        apiError.setException(exception);
      return apiError ;
    }

    public String getHostName(){
        try {
          return   InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.warn(e.getMessage());
        }
        return null;
    }


}
