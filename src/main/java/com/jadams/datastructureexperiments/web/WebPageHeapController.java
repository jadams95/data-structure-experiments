package com.jadams.datastructureexperiments.web;

import com.jadams.datastructureexperiments.domain.HeapQueue;
import com.jadams.datastructureexperiments.domain.HuffmanObj;
import com.jadams.datastructureexperiments.services.AntlrService;
import com.jadams.datastructureexperiments.services.HtmlParser;
import com.jadams.datastructureexperiments.services.WebScannerService;
//import org.apache.tomcat.util.json.JSONParser;
import jakarta.annotation.Nullable;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.arraycopy;
import static java.util.Arrays.stream;

@RestController
public class WebPageHeapController {


//    Logger loggerFactory = LoggerFactory.getLogger(We)
    @Autowired
    private WebScannerService webScannerService;

    @Autowired
    private AntlrService antlrService;

    @GetMapping(value = "/source", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> webPageGetSourceController(@RequestBody String domain) throws MalformedURLException, URISyntaxException {
        String urlResponse = webScannerService.getForObjects(domain);
        HeapQueue heapQueue = new HeapQueue(urlResponse);
        return new ResponseEntity<>(heapQueue.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/flip-tree", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> webPageGetFlipTreeController(@RequestBody String domain) throws IOException, URISyntaxException {
       String urlResponse = webScannerService.getForObjects(domain);
       HeapQueue heapQueue = new HeapQueue(urlResponse);
       heapQueue.flipTree();


       @Nullable
       HtmlParser.DocumentContext documentContext = antlrService.parseHtml(heapQueue.toString());
       char[] value = "JT".toCharArray();
       assert documentContext != null;
       char[] elementChars = documentContext.getText().toCharArray();
       arraycopy(value, 0, elementChars, 0, 2);
       return new ResponseEntity<>(Arrays.toString(elementChars), HttpStatus.OK);
    }


    @GetMapping(value = "/display-character-frequency-table", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<?> displayCharacterFrequencyTable(@RequestBody String domain) throws MalformedURLException, URISyntaxException {
        String urlResponse = webScannerService.getForObjects(domain);
        HeapQueue heapQueue = new HeapQueue(urlResponse);
        heapQueue.flipTree();


        @Nullable
        HtmlParser.DocumentContext documentContext = antlrService.parseHtml(heapQueue.toString());
        char[] value = "JT".toCharArray();
        assert documentContext != null;
        char[] elementChars = documentContext.getText().toCharArray();
        arraycopy(value, 0, elementChars, 0, 2);
        String newHtmlPage = new String(elementChars);
        char[] huffmanArr = newHtmlPage.toCharArray();
        HuffmanObj huffmanObj;
        for(int i = 0; i < huffmanArr.length - 1; i++) {
            int count = 0;
            if(huffmanArr[i] == huffmanArr[i+1]){
                count++;
                huffmanObj = new HuffmanObj(huffmanArr[i], count);
            }else {
                int initialCounter = 1;
                huffmanObj = new HuffmanObj(huffmanArr[i], count);
            }
        }
        HuffmanObj huffmanObj1 = HuffmanObj.HuffmanCoding.buildHuffmanTree(Arrays.toString(huffmanArr));
        return new ResponseEntity<>(huffmanObj1.toString(), HttpStatus.OK);    }

}
