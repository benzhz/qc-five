package com.benzhz.qcfive.common.exception;

import com.benzhz.qcfive.common.pojo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.common.exception
 * @Project：qc-five
 * @name：GlobalExceptionHandler
 * @Date：2025/2/16 22:15
 * @Filename：GlobalExceptionHandler
 */

/**
 * @description: 自定义异常处理
 * @author: DT
 * @date: 2021/4/19 21:51
 * @version: v1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ServiceException.class, ServerException.class})
    @ResponseBody
    public CommonResult bizExceptionHandler(HttpServletRequest req, ServiceException e) {
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return CommonResult.error(e.getCode(), e.getMessage());
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return CommonResult.error(500, e.getMessage());
    }
}
