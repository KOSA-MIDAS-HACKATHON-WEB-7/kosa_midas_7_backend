package com.backend.kosa_midas_7_backend.security.jwt;

import com.backend.kosa_midas_7_backend.entity.refresh.Refresh;
import com.backend.kosa_midas_7_backend.entity.refresh.repository.RefreshRepository;
import com.backend.kosa_midas_7_backend.exception.error.TokenUnauthorized;
import com.backend.kosa_midas_7_backend.security.auth.Details;
import com.backend.kosa_midas_7_backend.security.auth.DetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.exp.access}")
    private Long accessExp;

    @Value("${jwt.exp.refresh}")
    private Long refreshExp;

    private final RefreshRepository refreshRepository;

    private final DetailsService detailsService;

    private byte[] encodingKey() {
        return Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)).getBytes();
    }

    public Authentication getAuthentication(String accessToken) {

        Claims claims = parseClaims(accessToken);
        Details details = detailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public boolean validateToken(String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(encodingKey()).build().parseClaimsJws(accessToken);
            return true;
        } catch (Exception e) {
            throw TokenUnauthorized.EXCEPTION;
        }
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(encodingKey()).build().parseClaimsJws(accessToken).getBody();
        } catch (Exception e) {
            throw TokenUnauthorized.EXCEPTION;
        }
    }

    public String generateAccessToken(String userId) {
        return generateToken(userId, "access-token", accessExp);
    }

    public String generateRefreshToken(String userId) {
        String refreshToken = generateToken(userId, "refresh-token", refreshExp);
        refreshRepository.save(Refresh.builder()
                .userId(userId)
                .token(refreshToken)
                .build());
        return refreshToken;
    }

    public String generateToken(String userId, String tokenType, Long tokenExp) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + tokenExp * 1000))
                .setIssuedAt(new Date())
                .claim("typ", tokenType)
                .signWith(SignatureAlgorithm.HS256, encodingKey())
                .setSubject(userId)
                .compact();
    }
}
