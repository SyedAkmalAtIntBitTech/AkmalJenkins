/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.exception;

/**
 *
 * @author ajit
 */
public class ProcessFailed extends RuntimeException {

    private static final long serialVersionUID = 7032436818875287097L;

    /**
     * @param message
     */
    public ProcessFailed(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ProcessFailed(String message, Throwable cause) {
        super(message, cause);
    }

}
