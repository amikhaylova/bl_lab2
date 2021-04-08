package business.logic.lab2.controller;

import business.logic.lab2.exceptions.*;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(IncorrectDateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage incorrectDateException(IncorrectDateException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage restClientResponseException( RestClientResponseException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Кequest fails because of a server error response.");
        return message;
    }

    @ExceptionHandler(BookingFailException.class)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public ErrorMessage bookingFailException(BookingFailException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.I_AM_A_TEAPOT.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage conversionFailedException(ConversionFailedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(LoginExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage loginExistsException(LoginExistsException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(ModerationFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage moderationFailedException(ModerationFailedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage connectionException(ConnectException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage missingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage invalidPasswordException(InvalidPasswordException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage invalidLoginException(InvalidLoginException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage exception(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage());
        return message;
    }

}
