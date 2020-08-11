package com.lwc.test.filter.sys;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class LogAspect {
	private long startTime;
	@Pointcut("execution( * com.lwc.test.controller.sys..*.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog(){}//签名，可以理解成这个切入点的一个名称
    
    @Pointcut("execution( * com.lwc.test.service.sys..*.*(..))")//切入点描述，这个是uiController包的切入点
    public void serviceLog(){}
    
    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void beforeAspect(JoinPoint joinPoint) {
    	 this.startTime = System.currentTimeMillis();
         String className = joinPoint.getSignature().getDeclaringTypeName();
         String methodName = joinPoint.getSignature().getName();
         log.info("{}---------{},请求参数:{}",className,methodName,Arrays.toString(joinPoint.getArgs()));
    }
    
    @AfterReturning(returning = "result", pointcut = "controllerLog()")
    public void after(JoinPoint joinPoint,Object result) {
    	String resultString = "";
    	if(null != result) {
    		resultString = JSON.toJSONString(result);
    	}
    	long end = System.currentTimeMillis();
    	String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
    	log.info("{}--------{},耗时:{}ms,返回值为:{}",className,methodName,(end - startTime),resultString);
    }
   
    
    
    
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                 InetAddress inet=null;
                 try {
                     inet = InetAddress.getLocalHost();
                 } catch (UnknownHostException e) {
                     e.printStackTrace();
                 }
                     ip= inet.getHostAddress();
            }
        }  
        return ip;
    }
}
