package com.example.ui.aspect;


import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

//新建切面DoubleClickAspect
@Aspect
public class DoubleClickAspect {

    private final String POINT_CUT_DOUBLE_CLICK = "execution(@com.example.ui.aspect.DoubleClickAnnotation * *(..))";

    //切入点
    @Pointcut(POINT_CUT_DOUBLE_CLICK)
    public void doubleClick() {

    }

    //advice通知
    @Around("doubleClick()")
    public void execute(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            DoubleClickAnnotation annotation = signature.getMethod()
                    .getAnnotation(DoubleClickAnnotation.class);
            if (annotation != null) {
                //处理自己的逻辑
                Log.e("duck","doubleClick");
            }
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
