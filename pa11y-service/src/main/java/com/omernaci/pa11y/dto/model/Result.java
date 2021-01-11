package com.omernaci.pa11y.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "type",
        "typeCode",
        "message",
        "context",
        "selector",
        "runner",
        "runnerExtras"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @JsonProperty("code")
    public String code;

    @JsonProperty("type")
    public String type;

    @JsonProperty("typeCode")
    public Integer typeCode;

    @JsonProperty("message")
    public String message;

    @JsonProperty("context")
    public String context;

    @JsonProperty("selector")
    public String selector;

    @JsonProperty("runner")
    public String runner;

}
