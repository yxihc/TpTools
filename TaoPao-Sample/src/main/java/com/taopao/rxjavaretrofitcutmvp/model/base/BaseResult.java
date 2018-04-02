package com.taopao.rxjavaretrofitcutmvp.model.base;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 21:56
 * @Use: 服务器返回通用格式
 */
public class BaseResult<T> {
    public static final String onResultOk="0";
    private String errorCode;
    private String errorMsg;
    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
