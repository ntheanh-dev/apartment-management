package com.ou.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ou.dto.request.AuthenticationRequest;
import com.ou.dto.response.AuthenticationResponse;
import com.ou.exception.AppException;
import com.ou.exception.ErrorCode;
import com.ou.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:application-dev.properties")
@Slf4j
public class JwtService {

    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;

    public AuthenticationResponse authenticated(AuthenticationRequest authenticationRequest) {
        var authUser = userService.authUser(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );
        if (authUser == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        var token = generateToken(authUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("username",user.getUsername())
                .claim("userId",user.getId())
                .issuer("AnhTheNguyen")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(24,ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(env.getProperty("app.signerKey")));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(env.getProperty("app.signerKey").getBytes());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (JOSEException | ParseException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        return claims;
    }
    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        return claims.getExpirationTime();
    }

    public String getClaimValueFromToken(String token, String claimName) {
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            return claims.getStringClaim(claimName);
        } catch (ParseException | NullPointerException e) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        String username = getClaimValueFromToken(token,"username");

        return !(username == null || username.isEmpty() || isTokenExpired(token));
    }
}
