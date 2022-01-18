package com.crazyloong.cat.config;

import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.execption.RiBizExecption;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YPLI
 * @description
 * @date 2022/1/16 0016 14:26
 **/
@RestControllerAdvice
public class RestExceptionHandler extends ApiController {

    /**
     * 业务异常处理
     * @param e
     * @return ErrorInfo
     */
    @ExceptionHandler({RiBizExecption.class})
    public R businessExceptionHandler(HttpServletRequest request, RiBizExecption e){
        return failed(e.getMessage());
    }

    /**
     * 业务异常处理
     * @param e
     * @return ErrorInfo
     */
    @ExceptionHandler({Exception.class})
    public R businessExceptionHandler(HttpServletRequest request, Exception e){
        return failed(e.getMessage());
    }




}
