package org.yzh.web.model.vo;

/**
 * @Author: zlt
 */
public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(0),
    /**
     * 失败
     */
    ERROR(1);

    private Integer code;
    CodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
