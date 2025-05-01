package cn.kiana.unic.jwt;

import lombok.Data;

@Data
public class MyException extends RuntimeException{
    private final Integer code;

    /**
     * 通过状态码和异常信息创建异常对象
     */
    public MyException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    /**
     * 接受枚举类型对象
     */
    public MyException(cn.kiana.unic.jwt.ResponseEnum responseEnum){
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }
}