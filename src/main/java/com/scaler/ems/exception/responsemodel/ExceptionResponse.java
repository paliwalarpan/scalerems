package com.scaler.ems.exception.responsemodel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class ExceptionResponse implements Serializable {
    private String errorCode;
    private String errorMessage;
    private String requestedURI;
}
