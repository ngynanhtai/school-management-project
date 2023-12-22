package com.project.service;

import com.project.dto.common.UserDTO;

public interface AuthService {
    UserDTO findUserByEmail(String email);
}
