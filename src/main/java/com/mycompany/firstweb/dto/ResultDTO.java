
package com.mycompany.firstweb.dto;

import java.io.Serializable;

/**
 *
 * @author Artist-Code
 * @param <T>
 */
public class ResultDTO<T> implements Serializable{
    private final T data;
    private final boolean success;
    private final String errorMessage;

    public ResultDTO(T data, boolean success, String errorMessage) {
        this.data = data;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
