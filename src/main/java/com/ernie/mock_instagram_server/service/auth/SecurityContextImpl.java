package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.entity.User;
import com.ernie.mock_instagram_server.exception.NotAuthenticationException;
import com.ernie.mock_instagram_server.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Optional;


@Service
public class SecurityContextImpl implements SecurityContext {

    private final UserRepository userRepository;

    public SecurityContextImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Null
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.getUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Invalid current user authentication. (Username: %s)", username));
        }
        return optionalUser.get();
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null) && !isAnonymous();
    }

    @Override
    public boolean isAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken;
    }
}
