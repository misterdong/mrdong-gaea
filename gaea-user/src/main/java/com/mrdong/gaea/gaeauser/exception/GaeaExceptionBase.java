package com.mrdong.gaea.gaeauser.exception;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 18:35
 **/
public class GaeaExceptionBase extends RuntimeException{

    private static final long serialVersionUID = 2875154547655521486L;
    /**
     * 异常对应的http码
     */
    private int httpCode = HttpStatusCodes.STATUS_500;

    /**
     * 错误码
     */
    private String errCode = ErrorCodes.UNKNOW_EXCEPTION.getErrorCode();

    /**
     * 是否异常型错误，true-表示系统故障或数据出现异常 false-表示正常逻辑的失败分支
     */
    private boolean fault;

    /**
     * 链路追踪id
     */
    private String traceId;

    /**
     * 产生异常的应用名
     */
    private String appName;


    public GaeaExceptionBase(int httpCode, String errCode) {
        this.httpCode = httpCode;
        this.errCode = errCode;
    }

    public GaeaExceptionBase(String errCode, String errorMsg) {
        super(errorMsg);
        this.errCode = errCode;
    }

    public GaeaExceptionBase(int httpCode, String errCode, String errorMsg, Throwable e) {
        super(errorMsg, e);
        this.httpCode = httpCode;
        this.errCode = errCode;
    }

    public GaeaExceptionBase(int httpCode, String errCode, String errorMsg) {
        super(errorMsg);
        this.httpCode = httpCode;
        this.errCode = errCode;
    }

    public GaeaExceptionBase(String errorMsg) {
        super(errorMsg);
    }

    public GaeaExceptionBase(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public GaeaExceptionBase(Throwable cause) {
        super(cause);
    }

    public GaeaExceptionBase() {
    }


    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public boolean isFault() {
        return fault;
    }

    public void setFault(boolean isFault) {
        this.fault = isFault;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getAppName() {
        return appName;
    }
}
