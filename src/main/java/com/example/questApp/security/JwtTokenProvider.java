package com.example.questApp.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider
{
    @Value("${questapp.app.secret}")
    private String APP_SECRET;
    @Value("${qustapp.expires.in}")
    private long EXPIRES_IN;

    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal(); //Take userDetail object with Authentication
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN); // create a date for expire date
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)//when was it created and when will it be expire
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact(); // for create a key
    }

    Long getUserIdFromJwt(String token)// for extracted
    {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();//userId will be extracted using this key
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token) //to check if the token thet with the request front end is correct
    {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token); // token parsing preocess
            return ! isTokenExpired(token); // if expired return false
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
