package com.mrdong.gaea.gaeauser.exception;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 18:43
 **/
public class GaeaNoPerssionException extends GaeaExceptionBase {


    private final static int HTTP_CODE = HttpStatusCodes.STATUS_402;
    private final static String ERR_CODE = ErrorCodes.NO_PERMISSION.getErrorCode();
    private static final long serialVersionUID = 56764341251851931L;

    public GaeaNoPerssionException() {
        setupErrorCode();
    }

    public GaeaNoPerssionException(String errorMsg) {
        super(errorMsg);
        setupErrorCode();
    }

    public GaeaNoPerssionException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        setupErrorCode();
    }

    public GaeaNoPerssionException(Throwable cause) {
        super(cause);
        setupErrorCode();
    }

    private void setupErrorCode() {
        this.setHttpCode(HTTP_CODE);
        this.setErrCode(ERR_CODE);
    }
}
