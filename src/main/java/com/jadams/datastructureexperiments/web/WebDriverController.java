package com.jadams.datastructureexperiments.web;

import com.jadams.datastructureexperiments.domain.WebDriverWS;
import com.jadams.datastructureexperiments.services.WebScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//
//@RestController
//public class WebDriverController {
//
//
//
//    @Autowired
//    private WebScannerService scannerService;
//
//    Logger logger = LoggerFactory.getLogger(WebDriverController.class);
//
//
//    @PostMapping("/start-browser-and-driver")
//    public String startChromeBrowserAndWebDriver() {
//        return null;
//    }
//
//    @PostMapping("/execute-driver")
//    public String executeWebDriverCommand(@RequestBody WebDriverWS webDriverWS) {
//        try {
//            scannerService.getWebDriver(webDriverWS);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            return "Error: " + e.getMessage();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        return "Command received: " + webDriverWS.toString();
//    }
//
//
//    @PostMapping("/terminate-browser-and-driver")
//    public String terminateWebDriverCommand(@RequestBody WebDriverWS webDriverWS) {
//        return null;
//    }
//
//}
