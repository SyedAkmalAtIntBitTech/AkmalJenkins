package com.intbittech.responsemappers;

import java.io.Serializable;

/**
 *
 * @author Ajit
 */
public enum OperationStatusType implements Serializable{
    Success,
    RequestError,
    DataError;
}
