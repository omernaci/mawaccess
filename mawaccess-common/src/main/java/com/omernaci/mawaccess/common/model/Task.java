package com.omernaci.mawaccess.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "url",
        "standard"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("standard")
    private String standard;

}