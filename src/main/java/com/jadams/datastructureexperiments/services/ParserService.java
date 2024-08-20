package com.jadams.datastructureexperiments.services;

//import org.antlr.runtime.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

//@Service
//public class ParserService {
//
//    private String fileSource;
//    private String respQueueData;
//
//    public final LexerCustomService lexerCustomService;
//    public final ParserCustomService parserCustomService;
//
//    @Autowired
//    public ParserService(LexerCustomService lexerCustomService, ParserCustomService parserCustomService) {
//        this.lexerCustomService = lexerCustomService;
//        this.parserCustomService = parserCustomService;
//    }
//
//    public void initialize(String fileSource, String respQueueData) {
//        this.fileSource = fileSource;
//        this.respQueueData = respQueueData;
//
//        // Input String from Resp
//        this.parserCustomService.setInputStream(CharStreams.fromString(respQueueData));
//
//        // Set the Lexer and config
//        LexerInterpreter lexerInterpreter = new LexerInterpreter(
//                "HtmlLexer.g4",
//                this.lexerCustomService.getVocabulary(),
//                Arrays.asList(this.lexerCustomService.getRuleNames()),
//                Arrays.asList(this.lexerCustomService.getChannelNames()),
//                this.lexerCustomService.getATN(),
//                this.respQueueData
//        );
//
//        CommonTokenStream tokens = new CommonTokenStream(lexerInterpreter);
//        Arrays.stream(this.parserCustomService.getParseInfo().getDecisionInfo())
//                .collect(Collectors.toList());
//    }
//
//    public String evaluateTree() {
//        return null;
//    }
//
//    @Service
//    public static class LexerCustomService extends Lexer {
//        @Override
//        public String[] getRuleNames() {
//            return new String[0];
//        }
//
//        @Override
//        public String getGrammarFileName() {
//            return null;
//        }
//
//        @Override
//        public ATN getATN() {
//            return null;
//        }
//    }
//
//    @Service
//    public static class ParserCustomService extends Parser {
//        public ParserCustomService(TokenStream input) {
//            super(input);
//        }
//
//        @Override
//        public String[] getTokenNames() {
//            return new String[0];
//        }
//
//        @Override
//        public String[] getRuleNames() {
//            return new String[0];
//        }
//
//        @Override
//        public String getGrammarFileName() {
//            return null;
//        }
//
//        @Override
//        public ATN getATN() {
//            return null;
//        }
//    }
//}
