package com.zzw.biz.iCache.Utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pw
 * @version : DataResult.java,v 0.1 2022年04月23日 17:37
 */
@Data
public class WebResult<T> implements Serializable {

    /**
     * 数据
     */
    private T data;

    /**
     * 错误编码
     */
    private String rspCode = "success";

    /**
     * 错误消息
     */
    private String rspDesc;

    public static <T> WebResult<T> success(T obj) {
        WebResult<T> dataResult = new WebResult<>();
        dataResult.setData(obj);
        return dataResult;

    }

    public static <T> WebResult<T> success() {
        WebResult<T> dataResult = new WebResult<>();
        return dataResult;
    }


    public static <T> WebResult<T> fail(String errorMsg, String errorCode) {
        WebResult<T> dataResult = new WebResult<>();
        dataResult.setRspCode(errorCode);
        dataResult.setRspDesc(errorMsg);
        return dataResult;
    }


    public static <T> WebResult cloneStatus(WebResult result) {
        WebResult<T> dataResult = new WebResult<>();
        dataResult.setRspCode(result.getRspCode());
        dataResult.setRspDesc(result.getRspDesc());
        return dataResult;
    }

    public static <T> WebResult<T> fromResult(Result<T> result) {
        if (result == null) {
            return null;
        }
        if (result.getSuccess()) {
            return success(result.getData());
        } else {
            WebResult<T> dataResult = new WebResult<>();
            dataResult.setRspCode("fail");
            dataResult.setRspDesc(result.getExceptionMsg());
            dataResult.setData(result.getData());
            return dataResult;
        }
    }
}
