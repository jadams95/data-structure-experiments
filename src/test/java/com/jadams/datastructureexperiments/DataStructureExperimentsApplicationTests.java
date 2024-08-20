package com.jadams.datastructureexperiments;

import com.jadams.datastructureexperiments.domain.HeapQueue;
import com.jadams.datastructureexperiments.services.WebScannerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.LinkOption;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DataStructureExperimentsApplicationTests {

    @Autowired
    private WebScannerService webScannerService;


    Logger logger = LoggerFactory.getLogger(DataStructureExperimentsApplicationTests.class);
    @Test
    void contextLoads() throws MalformedURLException, URISyntaxException {

       String requestedObject = webScannerService.getForObjects("http://sjceltic.org/index.php");

        HeapQueue heapQueue = new HeapQueue(requestedObject);
        // how do we want to specify the url
        logger.info(requestedObject.toString());
        Assertions.assertNotNull(heapQueue.toString());
//        Assertions.assertTrue(he);
    }

}
