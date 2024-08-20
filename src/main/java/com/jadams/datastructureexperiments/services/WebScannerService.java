package com.jadams.datastructureexperiments.services;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
public class WebScannerService {


    @Autowired
    private RestTemplate restTemplate;

        Logger logger = LoggerFactory.getLogger(WebScannerService.class);

    public String getForObjects(String webPageUrl) throws MalformedURLException, URISyntaxException {

//        webPageUrl

//       String[] splitBySlash = webPageUrl.split("/");




        //make sure the first 7 characters are http://

//        var scheme = webPageUrl.substring(0, 7);
//        int i = 0;
//        while (i == webPageUrl.length())}
//        var host = webPageUrl.substring(8, );
//        logger.info("the supposive of scheme" + scheme);
//        logger.info("the host"+ host);
////        var testUrl = new StrwebPageUrl, null);
////        logger.info("PFRINT OUT URL" + webPageUrl);
//        logger.info("Aboslute URI or NOT --------------------> " + String.valueOf("http://" + webPageUrl + "/"));
        return restTemplate.getForObject(new URI(webPageUrl), String.class);
    }


//    public void getWebDriver(WebDriverWS webDriverWS) throws MalformedURLException, URISyntaxException {
//        StandardWebSocketClient client = new StandardWebSocketClient();
//        URI uri = new URI(webDriverWS.getServerUrl());
//        client.execute(new CustomTextHandler(), uri.toString());
//    }





}
