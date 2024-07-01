package com.jadams.datastructureexperiments.services;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;

import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Creating a Customer Parser Service for Antlr runtime to analyze the Response Heap Data
 */
public class ParserService {




    private String fileSource;
    private CharStream respQueueData;

    private LexerCustomService lexerCustomService;

    private ParserCustomService parserCustomService;

    private static class LexerCustomService extends Lexer{

        @Override
        public String[] getRuleNames() {
            return new String[0];
        }

        @Override
        public String getGrammarFileName() {
            return null;
        }

        @Override
        public ATN getATN() {
            return null;
        }
    }
    private static class ParserCustomService extends Parser {

        public ParserCustomService(TokenStream input) {
            super(input);
        }

        @Override
        public String[] getTokenNames() {
            return new String[0];
        }

        @Override
        public String[] getRuleNames() {
            return new String[0];
        }

        @Override
        public String getGrammarFileName() {
            return null;
        }

        @Override
        public ATN getATN() {
            return null;
        }
    }

    public ParserService(String fileSource, CharStream respQueueData) {
        this.fileSource = fileSource;
        this.respQueueData = respQueueData;


        // Input String from Resp
        this.parserCustomService.setInputStream(respQueueData);



        // set the Lexer and config
        LexerInterpreter lexerInterpreter = new LexerInterpreter("testhtml.g4", this.lexerCustomService.getVocabulary(), Arrays.asList(this.lexerCustomService.getRuleNames()), Arrays.asList(this.lexerCustomService.getChannelNames()), this.lexerCustomService.getATN(), this.respQueueData);


        CommonTokenStream tokens = new CommonTokenStream(lexerInterpreter);
//        logger.info("Logging heap queue object out after creation and heapify: \n" + Arrays.toString(lexerInterpreter._text.getBytes()));

        Arrays.stream(this.parserCustomService.getParseInfo().getDecisionInfo()).collect(Collectors.toList());
    }

    public String evaluateTree(){
        return null;
    }
}
