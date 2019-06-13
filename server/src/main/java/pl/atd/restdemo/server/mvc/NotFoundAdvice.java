package pl.atd.restdemo.server.mvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.atd.restdemo.server.exception.AgreementNotFoundException;

@ControllerAdvice
public class NotFoundAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AgreementNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        String bodyOfResponse = "Agreement not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
