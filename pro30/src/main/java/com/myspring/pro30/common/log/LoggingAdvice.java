package com.myspring.pro30.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	//target 메서드의 파라미터 등 정보를 출력합니다.
	@Before("execution(* com.myspring.pro30.*.service.*.*(..)) or "
	        + "execution(* com.myspring.pro30.*.dao.*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");
		
		//전달되는 모든 파라미터들을 Object의 배열로 가져옴
		logger.info("1:"+Arrays.toString(jp.getArgs()));
		
		//해당 Advice의 타입을 알아냄
		logger.info("2: "+jp.getKind());
		
		//실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용
		logger.info("3: "+jp.getSignature().getName());
		
		//target 객체를 알아낼 때 사용
		logger.info("4: "+jp.getTarget().toString());
		
		//Advice를 행하는 객체를 알아낼 때 사용
		logger.info("5: "+jp.getThis().toString());
		
	}
	
	@After("execution(* com.myspring.pro30.*.service.*.*(..)) or "
	        + "execution(* com.myspring.pro30.*.dao.*.*(..))")
	public void after(JoinPoint jp) {
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");
		
		//전달되는 모든 파라미터들을 Object의 배열로 가져옴
		logger.info("1: "+Arrays.toString(jp.getArgs()));
		
		//해당 Advice의 타입
		logger.info("2: "+jp.getKind());
		
		//실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용
		logger.info("3: "+jp.getSignature().getName());
		
		//target 객체를 알아낼 때 사용
		logger.info("4: "+jp.getTarget().toString());
		
		//Advice를 행하는 객체를 알아낼 때 사용
		logger.info("5: "+jp.getThis().toString());
	}
}
