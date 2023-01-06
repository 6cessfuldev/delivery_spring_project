package com.ezen.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

//	@ExceptionHandler(Exception.class)
//	public String except(Exception ex, Model model) {
//		
//		log.error("exception...."+ex.getMessage());
//		model.addAttribute("Exception", ex);
//		log.error(model.toString());
//		
//		return "error_page";
//		
//	}
//	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public String handle404(NoHandlerFoundException ex) {
//		
//		return "custom404";
//	}
//	
}
