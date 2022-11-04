package com.niit.TrashService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "archive does not exist")
public class TrashNotFoundException extends Exception{

}
