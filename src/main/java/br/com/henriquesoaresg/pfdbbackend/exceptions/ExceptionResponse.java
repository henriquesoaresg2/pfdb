package br.com.henriquesoaresg.pfdbbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private LocalDateTime generatedAt;
    private String message;
    private String details;
}
