package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.dto.model.auth.JwtDto;

public interface JwtService {
    String generateToken(JwtDto jwtDto);
}
