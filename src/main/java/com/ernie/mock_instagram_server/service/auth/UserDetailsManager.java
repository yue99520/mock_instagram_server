package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsDto;
import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsRegisterDto;
import com.ernie.mock_instagram_server.exception.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDetailsManager extends UserDetailsService {

    UserDetailsDto register(UserDetailsRegisterDto userDetailsRegisterDto) throws UserAlreadyExistsException;

    UserDetailsDto update(UserDetailsDto userDetailsDto) throws UsernameNotFoundException;

    UserDetailsDto addAuthorities(String username, List<Authority> authorities) throws UsernameNotFoundException;

    UserDetailsDto removeAuthorities(String username, List<Authority> authorities) throws UsernameNotFoundException;
}
