package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.dto.mapper.auth.UserDetailsMapper;
import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsDto;
import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsRegisterDto;
import com.ernie.mock_instagram_server.entity.GrantedAuthorityEntity;
import com.ernie.mock_instagram_server.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import com.ernie.mock_instagram_server.entity.User;
import com.ernie.mock_instagram_server.repository.GrantedAuthorityRepository;
import com.ernie.mock_instagram_server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserDetailServiceImpl implements UserDetailsManager {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final GrantedAuthorityRepository grantedAuthorityRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserDetailServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, GrantedAuthorityRepository grantedAuthorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.grantedAuthorityRepository = grantedAuthorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(String.format("Retrieve username \"%s\"", username));
        User user = getUserByUsername(username);
        List<GrantedAuthority> grantedAuthorities = grantedAuthorityRepository.getGrantedAuthorityEntitiesByUser(user);

        return UserDetailsMapper.toUserDetails(user, grantedAuthorities);
    }

    @Override
    public UserDetailsDto register(UserDetailsRegisterDto userDetailsRegisterDto) throws UserAlreadyExistsException{
        String username = userDetailsRegisterDto.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException(username);
        }

        User user = new User();

        user.setDisplayName(userDetailsRegisterDto.getUsername());

        setCredentials(user, userDetailsRegisterDto.getUsername(), userDetailsRegisterDto.getPassword());
        setDefaultUserStatus(user);

        User savedUser = userRepository.save(user);

        GrantedAuthorityEntity grantedAuthority = new GrantedAuthorityEntity();
        grantedAuthority.setUser(user);
        grantedAuthority.setAuthority(Authority.USER);
        grantedAuthorityRepository.save(grantedAuthority);

        List<GrantedAuthority> grantedAuthorities = grantedAuthorityRepository.getGrantedAuthorityEntitiesByUser(savedUser);

        return UserDetailsMapper.toUserDetails(savedUser, grantedAuthorities);
    }

    @Override
    public UserDetailsDto update(UserDetailsDto userDetailsDto) throws UsernameNotFoundException{
        User user = getUserByUsername(userDetailsDto.getUsername());

        setCredentials(user, userDetailsDto.getUsername(), userDetailsDto.getPassword());
        setUserAccountStatus(user, userDetailsDto.isAccountNonExpired(), userDetailsDto.isAccountNonLocked());
        setUserEnabled(user, userDetailsDto.isEnabled());
        setUserCredentialsStatus(user, userDetailsDto.isCredentialsNonExpired());


        User savedUser = userRepository.save(user);

        List<GrantedAuthority> grantedAuthorities = grantedAuthorityRepository.getGrantedAuthorityEntitiesByUser(savedUser);

        return UserDetailsMapper.toUserDetails(savedUser, grantedAuthorities);
    }

    @Override
    public UserDetailsDto addAuthorities(String username, List<Authority> authorities) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        for (Authority authority : authorities) {
            if (!grantedAuthorityRepository.existsByUserAndAuthority(user, authority.name())) {
                grantedAuthorityRepository.save(this.createAuthority(user, authority));
            }
        }

        List<GrantedAuthority> grantedAuthorities = grantedAuthorityRepository.getGrantedAuthorityEntitiesByUser(user);
        return UserDetailsMapper.toUserDetails(user, grantedAuthorities);
    }

    @Override
    public UserDetailsDto removeAuthorities(String username, List<Authority> authorities) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        for (Authority authority : authorities) {
            if (grantedAuthorityRepository.existsByUserAndAuthority(user, authority.name())) {
                grantedAuthorityRepository.deleteByUserAndAuthority(user, authority.name());
            }
        }

        List<GrantedAuthority> grantedAuthorities = grantedAuthorityRepository.getGrantedAuthorityEntitiesByUser(user);
        return UserDetailsMapper.toUserDetails(user, grantedAuthorities);
    }

    private User getUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> optionalUser = userRepository.getUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username \"%s\" not found.", username));
        }
        return optionalUser.get();
    }

    private void setCredentials(User user, String username, String password) {
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
    }

    private void setDefaultUserStatus(User user) {
        setUserAccountStatus(user, false, false);
        setUserCredentialsStatus(user, false);
        setUserEnabled(user, true);
    }

    private void setUserAccountStatus(User user, boolean isAccountExpired, boolean isAccountLocked) {
        user.setAccountExpired(isAccountExpired);
        user.setAccountLocked(isAccountLocked);
    }

    private void setUserCredentialsStatus(User user, boolean isCredentialsExpired) {
        user.setCredentialsExpired(isCredentialsExpired);
    }

    private void setUserEnabled(User user, boolean isEnabled) {
        user.setEnabled(isEnabled);
    }

    private GrantedAuthorityEntity createAuthority(User user, Authority authority) {
        GrantedAuthorityEntity grantedAuthorityEntity = new GrantedAuthorityEntity();
        grantedAuthorityEntity.setUser(user);
        grantedAuthorityEntity.setAuthority(authority);
        return grantedAuthorityEntity;
    }
}
