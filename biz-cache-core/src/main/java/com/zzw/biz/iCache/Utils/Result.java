package com.zzw.biz.iCache.Utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5972589869516657296L;
    /**
     * success不可能为null;
     */
    private Boolean success;

    private String exceptionCode;
    private String exceptionMsg;

    private T data;

    public static <T> Result success() {
        return success(null);
    }

    public static <T> Result success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result fail(String errorCode) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setExceptionCode(errorCode);
        return result;
    }

    public static <T> Result fail(String errorCode, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setExceptionCode(errorCode);
        result.setExceptionMsg(message);
        return result;
    }
}
