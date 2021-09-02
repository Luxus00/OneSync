package com.vnpt.eoffice.security.jwt;


import com.vnpt.eoffice.config.AppProperties;
import com.vnpt.eoffice.domain.UserPrincipal;
import com.vnpt.eoffice.security.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {
    private final AppProperties appProperties;

    public String createToken(Authentication authentication) {
        Object principal=authentication.getPrincipal();
        if(principal  instanceof UserPrincipal){
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
            String role = SecurityUtils
                    .getAuthorities(authentication)
                    .findFirst().orElse("0");
            log.info("ROLE: " + role);
            return Jwts.builder()
                    .setId(userPrincipal.getName())
                    .setSubject(role)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();
        }else {
            String id=(String) principal;
            String role=(String) authentication.getCredentials();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
            log.info("ROLE: " + role);
            log.info("ID: "+id);
            return Jwts.builder()
                    .setId(id)
                    .setSubject(role)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();
        }
    }

    /**
     * Token for forget password
     * @param username
     * @return
     */
    public String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        var principal=authentication.getPrincipal();
        if(principal  instanceof UserPrincipal ){
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpirationMsec());
            String role = SecurityUtils
                    .getAuthorities(authentication)
                    .findFirst().orElse("1");
            log.info("ROLE :" + role);
            return Jwts.builder()
                    .setId(userPrincipal.getName())
                    .setSubject(role)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();
        }else {
            String id=(String) principal;
            String role=(String) authentication.getCredentials();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpirationMsec());
            log.info("ROLE: " + role);
            log.info("ID: "+id);
            return Jwts.builder()
                    .setId(id)
                    .setSubject(role)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();
         }

    }

    public String getUserNameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(appProperties
                        .getAuth()
                        .getTokenSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getId();
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(appProperties
                            .getAuth()
                            .getTokenSecret())
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw new SignatureException(ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new MalformedJwtException(ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new ExpiredJwtException(null,null,ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new UnsupportedJwtException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
