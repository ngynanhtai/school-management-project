package com.project.controller;

import com.project.dto.common.JwtDTO;
import com.project.dto.common.LoginDTO;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.security.CustomUserDetails;
import com.project.security.UserDetailsServiceImpl;
import com.project.security.jwt.JwtUtils;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        if (StringUtils.isEmpty(loginDTO.getUserName()) || StringUtils.isEmpty(loginDTO.getPassword())) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String role = customUserDetails.getRole().stream().findFirst().get().getAuthority();
        String accessToken = jwtUtils.generateAccessToken(customUserDetails.getUsername(), role);
        String refreshToken = jwtUtils.generateRefreshToken(customUserDetails.getUsername(), role);

        return ResponseUtil.buildSuccess(
                JwtDTO
                        .builder()
                        .id(customUserDetails.getId())
                        .userName(customUserDetails.getUsername())
                        .fullName(customUserDetails.getFullName())
                        .userCode(customUserDetails.getUserCode())
                        .roleType(role)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessExpire(jwtUtils.getJwtAccessExpirationMs())
                        .refreshExpire(jwtUtils.getJwtRefreshExpirationMs())
                        .build()
        );
    }
}
