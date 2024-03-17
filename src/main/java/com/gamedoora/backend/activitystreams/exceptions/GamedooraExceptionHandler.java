package com.gamedoora.backend.activitystreams.exceptions;

import javax.lang.model.UnknownEntityException;
import org.hibernate.MappingException;
import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author aprajshekhar
 */
@ControllerAdvice
public class GamedooraExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {MappingException.class})
  protected ResponseEntity<Object> handleMappingException(MappingException ex, WebRequest request) {
    GamedooraExceptionResponseEntity bodyOfResponse =
            getBodyOfResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    return handleExceptionInternal(
            ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
  @ExceptionHandler(value = {UnknownEntityException.class})
  protected ResponseEntity<Object> handleUnknownEntityException(UnknownEntityException ex, WebRequest request) {
    GamedooraExceptionResponseEntity bodyOfResponse = getBodyOfResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
                return handleExceptionInternal(
            ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = {DataException.class})
  protected ResponseEntity<Object> handleDataException(DataException ex, WebRequest request) {
    GamedooraExceptionResponseEntity bodyOfResponse =
            getBodyOfResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    return handleExceptionInternal(
            ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  private  GamedooraExceptionResponseEntity getBodyOfResponse(Exception ex, HttpStatus httpStatus) {
    return GamedooraExceptionResponseEntity.builder()
            .status(httpStatus.name())
            .message(ex.getMessage())
            .details(ex.getMessage())
            .build();
  }

  @ExceptionHandler(value = {JDBCConnectionException.class})
  protected ResponseEntity<Object> handleJDBCConnectionException(JDBCConnectionException ex, WebRequest request) {
    GamedooraExceptionResponseEntity bodyOfResponse = getBodyOfResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);

    return handleExceptionInternal(
            ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = {QueryTimeoutException.class})
  protected ResponseEntity<Object> handleQueryTimeoutException(QueryTimeoutException ex, WebRequest request) {
    GamedooraExceptionResponseEntity bodyOfResponse = getBodyOfResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);

    return handleExceptionInternal(
            ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

}
