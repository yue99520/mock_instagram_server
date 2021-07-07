package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.dto.model.auth.JwtDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Calendar;

public class JwtServiceImpl implements JwtService{

    private AuthenticationManager authenticationManager;

    private final String KEY = "VincentIsRunningBlogForProgrammingBeginner";

    @Override
    public String generateToken(JwtDto jwtDto) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(jwtDto.getUsername(), jwtDto.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 2);

        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
        claims.setExpiration(calendar.getTime());
        claims.setIssuer("Programming Classroom");

        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }
}
