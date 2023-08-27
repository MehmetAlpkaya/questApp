package com.example.questApp.security;

import com.example.questApp.service.UserDetailsServiceImpl;
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
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter // Frontend den backend'e request geldiğinde birdenf azla default filtreleme gerçekleşir bizde bu filtrelemelere ek olarak jwt filter control ekliyoruz
{
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override // Upon receiving a request, it checks whether this request has been authorized. If not, it will reject it as unauthorized.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwtToken = extractJwtFromRequest(request);
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user = userDetailsService.loadUserById(id);
                if(user != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth); // The Security Context contains information about an authenticated user. Through the use of the SecurityContextHolder, we can access this user information throughout our entire application. When an unauthenticated user is encountered, an Authentication Exception is thrown.
                }
            }
        } catch(Exception e) {
            return;
        }
        filterChain.doFilter(request, response); //The doFilter method of the Filter is called by the container each time a request/response pair is passed through the chain due to a client request for a resource at the end of the chain.
    }
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
            return bearer.substring("Bearer".length() + 1);
        return null;
    }
}
