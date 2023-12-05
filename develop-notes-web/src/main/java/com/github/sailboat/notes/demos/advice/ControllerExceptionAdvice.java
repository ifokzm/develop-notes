package com.github.sailboat.notes.demos.advice;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.sailboat.notes.demos.enums.ResultCode;
import com.github.sailboat.notes.demos.exception.APIException;
import com.github.sailboat.notes.demos.utils.ResultVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({BindException.class})
    public ResultVo methodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVo(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVo apiExceptionHandler(APIException e) {
        log.error(e.getMessage(), e);
        return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new ResultVo(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg(), e.getMessage());
    }
}
