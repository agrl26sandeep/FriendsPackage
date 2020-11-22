package com.solution.domain;

/**
 * Class holds the response attributes.
 * @param <R> Custom object type
 *
 * @author Sandeep Agrawal
 */
public class Response<R> {

    private String message;
    private R data;

    public Response(String message, R data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
