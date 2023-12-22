package com.project.dto.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String userCode;
    private String password;
    private String fullName;
    private String email;
    private String roleType;
}
