package com.mrdong.gaea.gaeauser.exception;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 18:44
 **/
public class GaeaUserForbiddenException extends GaeaExceptionBase {


    private final static int HTTP_CODE = HttpStatusCodes.STATUS_403;
    private final static String ERR_CODE = ErrorCodes.USER_FORBIDDEN.getErrorCode();
    private static final long serialVersionUID = 8910381168513274651L;

    public GaeaUserForbiddenException() {
        setupErrorCode();
    }

    public GaeaUserForbiddenException(String errorMsg) {
        super(errorMsg);
        setupErrorCode();
    }

    public GaeaUserForbiddenException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        setupErrorCode();
    }

    public GaeaUserForbiddenException(Throwable cause) {
        super(cause);
        setupErrorCode();
    }

    private void setupErrorCode() {
        this.setHttpCode(HTTP_CODE);
        this.setErrCode(ERR_CODE);
    }
}
