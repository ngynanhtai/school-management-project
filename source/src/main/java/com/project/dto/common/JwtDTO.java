package com.project.dto.common;

import com.project.constant.Constant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDTO {
    private String accessToken;
    private String refreshToken;
    private int accessExpire;
    private int refreshExpire;
    private Long id;
    private String userName;
    private String userCode;
    private String fullName;
    private String roleType;
}
