package com.file.upload.ivsfileupload.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex)

	{
		Map<String, String> mp = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach((e) -> {

			mp.put(e.getField(), e.getDefaultMessage());
		});

		return mp;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(CustomExceptionProduct.class)
	public Map<String, String> doctorMappedException(CustomExceptionProduct ex) {
		Map<String, String> map = new HashMap<>();
		map.put("Message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, String> globalException(Exception ex) {
		Map<String, String> map = new HashMap<>();
		map.put("Message", ex.getMessage());

		return map;
	}

}
