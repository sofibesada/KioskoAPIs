package com.uade.tpo.Marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El producto que se intenta agregar está duplicado")
public class UserDuplicateException extends Exception { }
