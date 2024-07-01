package com.jadams.datastructureexperiments.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


//     URL finalQueryString = URI.create().toURL();
//        URLEncoder.encode(finalQueryString.toString());
        logger.trace("Aboslute URI or NOT --------------------> " + String.valueOf("http://" + webPageUrl + "/index.php"));
//        restTemplate.headForHeaders(webPageUrl, new HashMap<>().put("Content-Type", "text/html"));
        return restTemplate.getForObject(webPageUrl, String.class);
    }


}
