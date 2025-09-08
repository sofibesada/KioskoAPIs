package com.uade.tpo.Marketplace.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La orden no se encuentra")
public class OrderNotFoundException extends Exception{
    
}
