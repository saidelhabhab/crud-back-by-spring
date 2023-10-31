package com.tp.CRUD.config;


import com.tp.CRUD.service.JwtService;
import com.tp.CRUD.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")){
            token =  header.substring(7);

            try {
                username = jwtUtil.extractUserName(token);
            }catch (IllegalCallerException e){
                System.out.println("Unable to get Jwt Token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt token is expired");
            }
        }else {
            System.out.println("Jwt token does not start with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = jwtService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token,userDetails)){

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,
                                userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
