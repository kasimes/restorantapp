package com.ornek.restorant.restorantapp.exception;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String path;

    public ErrorResponse(LocalDateTime now, String message, String description) {
        this.timestamp = timestamp;
        this.message = this.message;
        this.path = path;
    }
}
