package com.jadams.datastructureexperiments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;
import java.util.Collections;

@SpringBootApplication
public class DataStructureExperimentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStructureExperimentsApplication.class, args);
    }

    @Configuration
    public class RestTemplateConfig {

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
