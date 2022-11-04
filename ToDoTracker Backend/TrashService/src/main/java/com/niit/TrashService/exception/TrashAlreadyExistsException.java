package com.niit.TrashService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "archive already exists")
public class TrashAlreadyExistsException extends Exception{
}
