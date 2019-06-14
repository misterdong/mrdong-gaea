package com.mrdong.gaea.gaeauser.exception;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 18:44
 **/
public class GaeaNotLoginException extends GaeaExceptionBase {


    private final static int HTTP_CODE = HttpStatusCodes.STATUS_401;
    private final static String ERR_CODE = ErrorCodes.NOT_LOGIN.getErrorCode();
    private static final long serialVersionUID = -7285897787840581505L;

    public GaeaNotLoginException() {
        setupErrorCode();
    }

    public GaeaNotLoginException(String errorMsg) {
        super(errorMsg);
        setupErrorCode();
    }

    public GaeaNotLoginException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        setupErrorCode();
    }

    public GaeaNotLoginException(Throwable cause) {
        super(cause);
        setupErrorCode();
    }

    private void setupErrorCode() {
        this.setHttpCode(HTTP_CODE);
        this.setErrCode(ERR_CODE);
    }
}
