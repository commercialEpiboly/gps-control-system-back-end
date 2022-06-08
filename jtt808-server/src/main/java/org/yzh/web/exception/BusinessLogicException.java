package org.yzh.web.exception;

/**
 * 业务逻辑异常
 * @Author: libo
 * @Date: 2022/03/22 14:14
 */
public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException() {
        super();
    }

    public BusinessLogicException(String s) {
        super(s);
    }

    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessLogicException(Throwable cause) {
        super(cause);
    }
}