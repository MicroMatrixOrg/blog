package com.luobo.util;

/**
 * @ClassName EmpryFileException
 * @Description 请描述类的业务用途
 * @Author davidmorgan
 * @Date 2021/1/26 10:17
 * @Version 1.0
 **/
public class EmpryFileException extends Exception{
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public EmpryFileException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmpryFileException(String message) {
        super(message);
    }
}
