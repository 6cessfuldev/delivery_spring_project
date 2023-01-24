package com.ezen.delivery.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LogAdvice {
	
	@Around("execution(* com.ezen.delivery.service.*.*(..))")
	public Object serviceMethodInfo( ProceedingJoinPoint pjp) {
		
		System.out.println("===============================");
		log.info("Target : " + pjp.getSignature().toShortString());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("===============================");
		
		return result; 
	}

	@Around("execution(* com.ezen.delivery.controller.*.*(..))")
	public Object controllerMethodInfo( ProceedingJoinPoint pjp) {
		
		System.out.println("===============================");
		log.info("Target : " + pjp.getSignature().toShortString());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("===============================");
		
		return result; 
	}

}


