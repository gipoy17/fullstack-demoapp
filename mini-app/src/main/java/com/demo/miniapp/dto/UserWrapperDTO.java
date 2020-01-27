package com.demo.miniapp.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapperDTO implements Serializable {
    private static final long serialVersionUID = -3202159258898788113L;
    private List<UserDTO> users;
}
