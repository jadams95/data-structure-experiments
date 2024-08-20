package com.jadams.datastructureexperiments;


//import com.jadams.datastructureexperiments.config.CustomTextHandler;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;
import java.util.Collections;

@SpringBootApplication
public class DataStructureExperimentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStructureExperimentsApplication.class, args);
    }

    @Configuration
    @EnableAsync
    public static class RestTemplateConfig {
//
//        @Override
//        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//            registry.addHandler(myHandler(), "/devtools/page")
//                    .setAllowedOrigins("*")
//                    .addInterceptors(myHandshakeInterceptor())
//                    .setHandshakeHandler(new DefaultHandshakeHandler());
//        }
//
//        @Bean
//        public WebSocketHandler myHandler() {
//            return new CustomTextHandler();
//        }
//
//        @Bean
//        MyHandshakeInterceptor myHandshakeInterceptor(){ return new MyHandshakeInterceptor();}
//

        // Configuring a RestTemplate bean with timeout settings
        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) {

            DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
            defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

            return builder.uriTemplateHandler(defaultUriBuilderFactory)
                    .setConnectTimeout(Duration.ofMillis(3000)) // Set connection timeout to 3000 milliseconds
                    .setReadTimeout(Duration.ofMillis(3000))    // Set read timeout to 3000 milliseconds
                    .build();
        }
    }
}
