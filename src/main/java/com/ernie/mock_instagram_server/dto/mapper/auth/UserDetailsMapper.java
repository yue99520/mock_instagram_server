package com.ernie.mock_instagram_server.dto.mapper.auth;

import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsDto;
import com.ernie.mock_instagram_server.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserDetailsMapper {

    public static UserDetailsDto toUserDetails(User user, List<GrantedAuthority> grantedAuthorities) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();

        userDetailsDto.setUsername(user.getUsername());
        userDetailsDto.setPassword(user.getPassword());

        userDetailsDto.setGrantedAuthorities(grantedAuthorities);

        userDetailsDto.setEnabled(user.getEnabled());
        userDetailsDto.setAccountLocked(user.getAccountLocked());
        userDetailsDto.setAccountExpired(user.getAccountExpired());
        userDetailsDto.setCredentialsExpired(user.getCredentialsExpired());

        return userDetailsDto;
    }
}
