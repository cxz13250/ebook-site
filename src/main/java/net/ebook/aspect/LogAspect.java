package net.ebook.aspect;

import net.ebook.annotation.Log;
import net.ebook.common.constants.OperationStatus;
import net.ebook.web.logic.UserOperationLogic;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午9:40 2018/5/16
 * @Modified By:
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    UserOperationLogic operationLogic;

//    @Pointcut("execution(public * net.ebook.web.ctrl.*.*(..))")
//    public void webLog(){
//    }

    @Pointcut("execution(public * net.ebook.web.ctrl.BookController.getList(..))")
    public void webLog(){

    }

//    @Before("@annotation(log)")
//    public void doBefore(JoinPoint jt, Log log){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        operationLogic.recordUserOperation(request, log.content());
//    }

    @After("@annotation(log)")
    public void doAfter(JoinPoint jt, Log log){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        operationLogic.recordUserOperation(request, log.content());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        operationLogic.recordUserOperation(request, OperationStatus.BOOK_LIST);
        System.out.println(request.getSession().getAttribute("userId"));
        //System.out.println("方法的返回值 : " + ret);
    }
}
