package com.zerobase.auction_project.configuration.filter;

import com.zerobase.auction_project.jwt.common.UserVo;
import com.zerobase.auction_project.jwt.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String loginToken = request.getHeader("X-AUTH-TOKEN");
        if (provider.validateToken(loginToken)) {
            UserVo userVo = provider.getUserVo(loginToken);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userVo.getId(), null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}