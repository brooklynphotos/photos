package photos.brooklyn.controller;

public class ErrorResponse {
    private int errorCode = -1;
    private String message;
    private Object detail;
    public ErrorResponse() {}
    public ErrorResponse(Exception ex) {
        this.message = ex.getMessage();
        this.detail = ex;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getDetail() {
        return detail;
    }
    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
