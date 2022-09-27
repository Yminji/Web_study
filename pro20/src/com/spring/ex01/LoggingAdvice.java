package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor{
	public Object invoke(MethodInvocation invocation) throws Throwable{
		System.out.println("[메서드 호출 전 : LoggingAdvice");
		System.out.println(invocation.getMethod()+"메서드 호출 전");
		
		//invocation.proceed() 기준으로 메서드 호출 전과 후를 분리하여 로그 메시지 출력
		Object object = invocation.proceed(); //invocation를 이용해 메서드 호출
		
		System.out.println("[메서드 호출 후 : LoggingAdvice");
		System.out.println(invocation.getMethod()+"메서드 호출 후");
		return object;
	}
}
