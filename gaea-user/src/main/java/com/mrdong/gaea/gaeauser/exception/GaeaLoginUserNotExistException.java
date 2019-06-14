package com.mrdong.gaea.gaeauser.exception;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 18:43
 **/
public class GaeaLoginUserNotExistException extends GaeaExceptionBase {


    private final static int HTTP_CODE = HttpStatusCodes.STATUS_402;
    private final static String ERR_CODE = ErrorCodes.LOGIN_USER_NOT_EXIST.getErrorCode();
    private static final long serialVersionUID = 899778004183400700L;

    public GaeaLoginUserNotExistException() {
        setupErrorCode();
    }

    public GaeaLoginUserNotExistException(String errorMsg) {
        super(errorMsg);
        setupErrorCode();
    }

    public GaeaLoginUserNotExistException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        setupErrorCode();
    }

    public GaeaLoginUserNotExistException(Throwable cause) {
        super(cause);
        setupErrorCode();
    }

    private void setupErrorCode() {
        this.setHttpCode(HTTP_CODE);
        this.setErrCode(ERR_CODE);
    }
}
