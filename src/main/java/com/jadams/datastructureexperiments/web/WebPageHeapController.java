package com.jadams.datastructureexperiments.web;

import com.jadams.datastructureexperiments.domain.HeapQueue;
import com.jadams.datastructureexperiments.services.WebScannerService;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNType;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.tree.Tree;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.Collections;

@RestController
public class WebPageHeapController {
    @Autowired
    private WebScannerService webScannerService;


//    @Autowired
//    Environment environment;
    Logger logger = LoggerFactory.getLogger(WebPageHeapController.class);


    @GetMapping(value = "/source", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> webPageGetSourceController(@RequestBody String domain) throws MalformedURLException, URISyntaxException {
        String urlResponse = webScannerService.getForObjects(domain);
        HeapQueue heapQueue = new HeapQueue(urlResponse);
        logger.info("Logging heap queue object out after creation and heapify: \n" + heapQueue.toString());
        return new ResponseEntity<>(heapQueue.toString(), HttpStatus.OK);
    }


    @Deprecated
    @GetMapping(value = "/flip-tree", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> webPageGetFlipTreeController(@RequestBody String domain) throws IOException, URISyntaxException {

        String urlResponse = webScannerService.getForObjects(domain);
        // creates Heap tree
        HeapQueue heapQueue = new HeapQueue(urlResponse);
        heapQueue.flipTree();
        int ch = 0x10FFFF;
        String s = new String(Character.toChars(ch));

        logger.info("UTF Character at 65536" + s);
//        var inputStream = CharStreams.fromString(heapQueue.toString());
//        var test = new ATN(ATNType.LEXER, Character.getNumericValue(65536));
//
//        BufferedTokenStream bufferedTokenStream = new CommonTokenStream().setTokenSource();
//
//
//
//
//
//        LexerInterpreter lexerInterpreter = new LexerInterpreter("testhtml.g4",
//              test, inputStream);
//        logger.info("Logging heap queue object out after creation and heapify: \n" + Arrays.toString(lexerInterpreter._text.getBytes()));
        return new ResponseEntity<>(heapQueue.toString(), HttpStatus.OK);
    }




}
