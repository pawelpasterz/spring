package pl.pasterz.crmspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pasterz.crmspring.error.CustomerErrorResponse;
import pl.pasterz.crmspring.error.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex) {

    CustomerErrorResponse response = new CustomerErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        System.currentTimeMillis()
    );

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<CustomerErrorResponse> catchAll(Exception ex) {

    CustomerErrorResponse response = new CustomerErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        System.currentTimeMillis()
    );

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
