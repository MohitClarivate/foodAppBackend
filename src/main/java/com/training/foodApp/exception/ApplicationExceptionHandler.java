package com.training.foodApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.foodApp.util.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(IdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Id not found in database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("No such Id found");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Invalid User");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Invalid User");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MenuNotFound.class)
	public ResponseEntity<ResponseStructure<String>> menuNotFound(MenuNotFound exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Invalid Menu");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Invalid Menu");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AlreadyExist.class)
	public ResponseEntity<ResponseStructure<String>> alreadyExist(AlreadyExist exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Email already exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Email already exist");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
