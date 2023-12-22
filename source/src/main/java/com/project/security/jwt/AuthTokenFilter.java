package com.project.security.jwt;

import com.project.constant.Constant;
import com.project.enums.MessageCodeEnum;
import com.project.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Log4j2
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!checkAuthorization(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = getJWT(request);
            Claims claims = jwtUtils.getClaims(jwt);
            String username = claims.getSubject();
            if (StringUtils.isEmpty(username)) {
                response.setStatus(MessageCodeEnum.UNAUTHORIZED.getCode());
                return;
            }

            if (!StringUtils.isEmpty(jwt) && jwtUtils.validateToken(jwt)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
                System.out.println("Authorities granted : " + authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(MessageCodeEnum.UNAUTHORIZED.getCode());
                return;
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkAuthorization(HttpServletRequest request) {
        return request.getHeader(Constant.AUTHORIZATION) != null;
    }

    private String getJWT(HttpServletRequest request) {
        String headerAuth = request.getHeader(Constant.AUTHORIZATION);
        if (!StringUtils.isEmpty(headerAuth) && headerAuth.startsWith(Constant.BEARER)) {
            return headerAuth.substring(Constant.BEARER.length());
        }
        return null;
    }
}
