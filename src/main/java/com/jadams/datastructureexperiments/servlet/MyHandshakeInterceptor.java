package com.jadams.datastructureexperiments.servlet;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//import org.springframework.web.socket.WebSocketHandler;
//
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
//public class MyHandshakeInterceptor implements HandshakeInterceptor {
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        return false;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//
//        try {
//            response.flush();
//            response.setStatusCode(HttpStatusCode.valueOf(200));
//            response.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}