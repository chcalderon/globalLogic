package com.example.demo.globallogic.userApi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JavaUtil {

    public static boolean validatePassword(String password) {
        String pattern = "^[A-Z]{1}[a-z]{1,100}[0-9]{2}$";

        boolean result = false;
        if (password.matches(pattern)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }



    public static boolean validateEmail(String email) {

        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        return email.matches(pattern);
    }

    public static String getJWTToken(String username) {
        String secretKey = "GlobalLogicSecretKey";
        List<GrantedAuthority> grantedAuth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuth.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
