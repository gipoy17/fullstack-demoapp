package com.demo.miniapp.presentation;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T extends Object> implements Serializable {
    private static final long serialVersionUID = 6867258964764828502L;

    private HttpStatus status;
    private T data;
}
