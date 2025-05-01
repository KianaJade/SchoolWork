package cn.kiana.unic.jwt;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionConfig{
    @ExceptionHandler(MyException.class)
    public R<MyException> handle(MyException e){
        e.printStackTrace();
        return R.exception(e.getCode(),e.getMessage());
    }
}