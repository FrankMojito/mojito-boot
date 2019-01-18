package com.mojito.mojitoboot.web.controller;

import com.google.common.collect.Maps;
import com.mojito.mojitoboot.common.error.BusinessException;
import com.mojito.mojitoboot.common.error.EmBusinessError;
import com.mojito.mojitoboot.common.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Auther: Mojito
 * @Date: 2019/1/18 00:54
 * @Description: 使用springboot的 @ExceptionHandler 钩子，捕获被抛到tomcat容器的异常。
 *                定义的基类为Exception，判断是否为业务异常或者为其他异常。
 */
public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object exceptionHandler(Exception ex){
        HashMap<String, Object> responseData = Maps.newHashMap();
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }else{
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.fail(responseData,"fail");
    }
}
