package com.i502tech.gitclub.api.http.api;

import com.google.gson.annotations.SerializedName;

/**
 * 获取json数据基类
 */

public class BaseResponse<T> implements ErrorStatus{

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;

    /**
     * 是否请求成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return code == OK;
    }

    public boolean isTokenFail() {
        return code == 405;
    }

    public int getStatus() {
        return code;
    }

    public void setStatus(int status) {
        this.code = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
