package com.tp.CRUD.util;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {


    private static final String SECRET_KEY = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    private static final int TOKEN_VALIDITY = 3600;

    // Generates a JWT token
    public  String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userName);
    }

    // Creates a JWT token
    public String createToken(Map<String,Object> claims, String userName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * TOKEN_VALIDITY))
                .signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    }


    // Gets the signing key
    public Key getSignKey(){
        byte[] keyByte= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);

    }


    // Extracts the username from a token
    public  String extractUserName(String token){

        return extractClaim(token,Claims::getSubject);
    }

    // Extracts a claim from a token using a resolver function
    public  <T> T extractClaim(String token, Function<Claims,T> claimResolver){

        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    // Extracts all claims from a token
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();

    }

    // Checks if a token is expired
    public  boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }


   // Extracts the token's expiration date
    private   Date extractExpiration(String token){

        return extractClaim(token,Claims::getExpiration);
    }


    // Validates a token against user details
    public  boolean validateToken(String token, UserDetails userDetails){
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /*
    public  String getUserNameFromToken(String token){

        return getClaimFromToken(token,Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){

        final  Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private  Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    }

    public  boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private   boolean isTokenExpired(String token){
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.ES512,SECRET_KEY)
                .compact();
    }

     */
}

