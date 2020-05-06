package i.am.whp.exception;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
public class CommonException extends RuntimeException {

    private Integer errorCode;
    private String msg;

    public CommonException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
