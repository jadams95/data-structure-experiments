package com.jadams.datastructureexperiments.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class WebDriverWS {
//    private Integer id;
    private String method;

    @JsonProperty("serverUrl")
    private String serverUrl;

    @JsonProperty("params")
    private JsonNode parameters;

    // Default constructor
    public WebDriverWS() {}

    // Parameterized constructor
    public WebDriverWS(String method, String serverUrl, JsonNode parameters) {
        this.method = method;
        this.serverUrl = serverUrl;
        this.parameters = parameters;
    }

    // Getters and setters
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public JsonNode getParameters() {
        return parameters;
    }

    public void setParameters(JsonNode parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "{" +
                "method: '" + method + '\'' +
                ", serverUrl: '" + serverUrl + '\'' +
                ", parameters: " + parameters +
                '}';
    }
}