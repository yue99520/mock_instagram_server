package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDetailsManager {

    UserDetailsDto register(UserDetailsDto userDetailsDto);

    UserDetailsDto update(UserDetailsDto userDetailsDto) throws UsernameNotFoundException;

    UserDetailsDto addAuthorities(String username, List<Authority> authorities) throws UsernameNotFoundException;

    UserDetailsDto removeAuthorities(String username, List<Authority> authorities) throws UsernameNotFoundException;
}
