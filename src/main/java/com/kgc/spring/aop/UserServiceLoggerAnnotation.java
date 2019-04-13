package com.kgc.spring.aop;

import java.util.Arrays;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面程序
 * 定义增强通知的类
 */
@Aspect	//声明一个切面程序
public class UserServiceLoggerAnnotation {

	private static final Logger log = Logger.getLogger(UserServiceLoggerAnnotation.class);

	@Pointcut("execution(* com.kgc.spring.service..*.*(..))")
	public void pointcut() {
		//用一个方法表示定义一个切入点
	}
	
	@Before("pointcut()") //配置前置通知的切入点(方法名)
	public void before(JoinPoint jp) {
		log.info("调用 " + jp.getTarget() + " 的 " + jp.getSignature().getName() + " 方法");
		log.info("方法入参：" + Arrays.toString(jp.getArgs()));				
	}

	@AfterReturning(pointcut="pointcut()",returning="result")
	public void afterReturning(JoinPoint jp, Object result) {
		log.info("方法返回值：" + result);
	}

	@AfterThrowing(pointcut="pointcut()",throwing="e")
	public void afterThrow(JoinPoint jp, Exception e) {
		log.error(jp.getSignature().getName() + " 方法发生异常: " + e);
	}

	@After("pointcut()")
	public void after(JoinPoint jp) {
		log.info(jp.getSignature().getName() + " 方法运行结束。");
	}

	//环绕通知需要将切入点方法的返回值进行返回
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		try {
			long begin = System.currentTimeMillis();
			//执行切入点的方法,并拿到返回值值
			Object result = jp.proceed();
			long end = System.currentTimeMillis();
			log.info("执行时间是" + (end-begin) +"毫秒" );
			//返回结果
			return result;
		} catch (Throwable e) {
			log.error("==>" + jp.getSignature().getName() + " 方法发生异常：" + e);
			throw e;
		} finally {
            log.info("==>" + jp.getSignature().getName() + " 方法结束执行。");
        }
	}
	
}