package com.vikram.code.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String message;

    private Boolean success;

    private HttpStatus httpStatus;
}
