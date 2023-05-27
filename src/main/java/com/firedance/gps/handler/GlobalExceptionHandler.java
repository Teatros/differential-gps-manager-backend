package com.firedance.gps.handler;

import com.firedance.gps.handler.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局的异常处理类。
 *
 * @author leiwei
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final static String USER_ID = "User-Id";

    /**
     * 内部微服务异常统一处理方法
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result processMicroServiceException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Result result = null;

        if (e instanceof Exception) { ;
            result = new Result<>("500", "网络异常",
                "ERROR", e.getMessage());
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        return result;
    }
}
