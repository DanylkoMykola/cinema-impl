package com.example.cinemaimpl.exception.handler;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private String timeStamp;
    @JsonIgnore
    private String trace;
    private String exception;

    /**
     * Constructor with parameters.
     */
    public ExceptionResponse(Map<String, Object> errorAttributes) {
        this.setException((String) errorAttributes.get("exception"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTimeStamp(errorAttributes.get("timestamp").toString());
        this.setTrace((String) errorAttributes.get("trace"));
    }
}
