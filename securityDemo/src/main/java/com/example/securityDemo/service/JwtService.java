package com.example.securityDemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private Long expiry;


    public String generateToken(Authentication authentication) {
        Instant instant = Instant.now();

        List<String> authorities = authentication.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).toList();
// for payload
        JwtClaimsSet claims = JwtClaimsSet.builder().
                issuer(issuer)
                .issuedAt(instant)
                .expiresAt(instant.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("authorities", authorities)
                        .build();

        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));

        return jwt.getTokenValue();
    }

}
