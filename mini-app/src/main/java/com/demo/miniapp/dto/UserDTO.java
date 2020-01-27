package com.demo.miniapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 8848903200539646589L;

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

}
