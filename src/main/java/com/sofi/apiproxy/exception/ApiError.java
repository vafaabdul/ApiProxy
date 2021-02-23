package com.sofi.apiproxy.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiError {
    private long timestamp = Instant.now().getEpochSecond();
    private int code;
    private String error;

}
