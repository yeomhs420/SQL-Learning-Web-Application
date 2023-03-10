package com.example.demo.config.aop;


import com.example.demo.service.TestService;
import com.example.demo.vo.TestResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Configuration
@Aspect
public class AdvisorConfig {


    @Autowired
    TestService testService;

    @Pointcut("execution(* com.example.demo.service.sampledata..get*(..))")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public List<Object> logAdvisor(ProceedingJoinPoint joinPoint) throws Throwable {
        List<Object> returnList;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod(); // Pointcut으로 선정한 메소드 불러오기
        try {
            returnList = (List<Object>) joinPoint.proceed();
            System.out.println(method.getDeclaringClass() + "." + method.getName() + "()에서 반환 받은 데이터 수 : " + returnList.size());
        } catch (Throwable throwable) {
            throw throwable;
        }
        return returnList;
    }

    @Pointcut("execution(* com.example.demo.service.GradingService.grade(..))")
    public void gradepointcut() {
    }


    @AfterReturning(value = "gradepointcut()", returning = "testResult")
    public void changeStatus(JoinPoint joinPoint, TestResult testResult) {
        Map<String, Object> userAnswer = (Map<String, Object>)joinPoint.getArgs()[0];
        int unit_Num = Integer.parseInt(userAnswer.get("unit").toString());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (testResult.getCorrectCount() == testResult.getQuestionList().size()) testService.setStatus(unit_Num);

    }
}
