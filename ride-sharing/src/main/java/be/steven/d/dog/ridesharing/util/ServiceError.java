package be.steven.d.dog.ridesharing.util;

public class ServiceError {
    private Integer code;
    private String message;

    public ServiceError() {
    }

    public ServiceError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}