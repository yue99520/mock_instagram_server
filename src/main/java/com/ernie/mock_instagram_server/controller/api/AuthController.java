package com.ernie.mock_instagram_server.controller.api;

import com.ernie.mock_instagram_server.controller.api.request.UserRegisterRequest;
import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsDto;
import com.ernie.mock_instagram_server.dto.model.auth.UserDetailsRegisterDto;
import com.ernie.mock_instagram_server.exception.UserAlreadyExistsException;
import com.ernie.mock_instagram_server.service.auth.UserDetailsManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserDetailsManager userDetailsManager;

    public AuthController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDetailsDto> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) throws UserAlreadyExistsException {
        UserDetailsRegisterDto userDetailsRegisterDto = new UserDetailsRegisterDto();
        userDetailsRegisterDto.setUsername(userRegisterRequest.getUsername());
        userDetailsRegisterDto.setPassword(userRegisterRequest.getPassword());


        UserDetailsDto userDetailsDto = userDetailsManager.register(userDetailsRegisterDto);
        return new ResponseEntity<>(
                userDetailsDto,
                HttpStatus.OK
        );
    }


}
