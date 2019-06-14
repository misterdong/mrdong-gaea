package com.mrdong.gaea.gaeauser.exception;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 18:38
 **/
public class GaeaLoginBadPasswordException extends GaeaExceptionBase {


    private final static int HTTP_CODE = HttpStatusCodes.STATUS_402;
    private final static String ERR_CODE = ErrorCodes.LOGIN_BAD_PASSWORD.getErrorCode();

    private static final long serialVersionUID = 324119723713056764L;

    public GaeaLoginBadPasswordException() {
        setupErrorCode();
    }

    public GaeaLoginBadPasswordException(String errorMsg) {
        super(errorMsg);
        setupErrorCode();
    }

    public GaeaLoginBadPasswordException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        setupErrorCode();
    }

    public GaeaLoginBadPasswordException(Throwable cause) {
        super(cause);
        setupErrorCode();
    }

    private void setupErrorCode() {
        this.setHttpCode(HTTP_CODE);
        this.setErrCode(ERR_CODE);
    }
}
