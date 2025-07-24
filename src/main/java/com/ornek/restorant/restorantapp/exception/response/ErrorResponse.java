package com.ornek.restorant.restorantapp.exception.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private LocalDateTime timestamp;
    private String message;
    private String errorCode;

    public ErrorResponse(int status, String errorCode, String message, LocalDateTime timestamp) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
    }
}
