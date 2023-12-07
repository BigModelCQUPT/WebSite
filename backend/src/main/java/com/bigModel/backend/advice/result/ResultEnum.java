package com.bigModel.backend.advice.result;

/**
 * 常用结果的枚举
 */
public enum ResultEnum implements IResult {
    SUCCESS(200, "success"),
    VALIDATE_FAILED(400, "参数错误，参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "访问拒绝"),
    NOT_FOUND(404, "Not Found"),
    COMMON_FAILED(500, "服务器系统错误");

    private Integer code;
    private String message;


    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
