package io.mbnakaya.imdplay.transport.interceptors;

import io.mbnakaya.imdplay.domain.exceptions.UnauthorizedException;
import io.mbnakaya.imdplay.transport.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UnauthorizedException.class})
    protected ResponseEntity<ErrorResponse> handleUnauthorizedException(RuntimeException ex, WebRequest req) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), 401), HttpStatus.UNAUTHORIZED);
    }
}
