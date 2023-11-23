//package com.zerobase.auction_project.configuration.filter;
//
//import com.zerobase.auction_project.jwt.common.UserVo;
//import com.zerobase.auction_project.jwt.config.JwtAuthenticationProvider;
//import com.zerobase.auction_project.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
////    /api/*  or  /api/users/* ??
//@WebFilter(urlPatterns = "/api/users/*")
//@RequiredArgsConstructor
//public class UserFilter implements Filter {
//
//    private final JwtAuthenticationProvider provider;
//    private UserService userService;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        String loginToken = req.getHeader("X-AUTH-TOKEN");
//        if (!provider.validateToken(loginToken)) {
//            throw new ServletException("Invalid Access");
//        }
//
//        UserVo userVo = provider.getUserVo(loginToken);
//        userService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
//                .orElseThrow(
//                        () -> new ServletException("Invalid Access")
//                );
//        chain.doFilter(request, response);
//
//
//    }
//}
