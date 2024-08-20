package com.jadams.datastructureexperiments.config;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.antlr.v4.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.xnio.FileSystemWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;



@Component
public class AntlrClassGenerator {

    @Autowired
    private Environment environment;


    Logger logger = LoggerFactory.getLogger(AntlrClassGenerator.class);
    @PostConstruct
    public void generateAndCompileLexerAndParser() throws IOException, ClassNotFoundException {
        String projectDir = System.getProperty("user.dir");

        // Define the paths to the grammar files
        String lexerGrammarFile = projectDir + "/src/main/resources/grammar/HtmlLexer.g4";
        String parserGrammarFile = projectDir + "/src/main/resources/grammar/HtmlParser.g4";

        // Check if grammar files exist
        if (!new File(lexerGrammarFile).exists() || !new File(parserGrammarFile).exists()) {
            throw new IOException("Grammar files not found in specified directory.");
        }

        // Set up Antlr tool arguments
        String[] args = {
                "-o", projectDir + "/src/main/java/com/jadams/datastructureexperiments/services",
                "-package", "com.jadams.datastructureexperiments.services",
                "-listener",
                "-visitor",
                lexerGrammarFile,
                parserGrammarFile
        };

        // Run the Antlr tool
        Tool antlr = new Tool(args);
        antlr.processGrammarsOnCommandLine();

        System.out.println("Antlr generation complete.");
    }
    @PreDestroy
    public void cleanUpEnvironment(){
        // should be 10 files in services that need to be deleted on cleanup of the container

//        Environment environment1 = environment;
//        if(Objects.equals(environment1.getProperty("platform"), "WINDOWS")){
//
//        }

        String filePath = String.join("", "src/main/java/com/jadams/datastructureexperiments/services");
    }
}