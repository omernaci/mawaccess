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
        "total",
        "error",
        "warning",
        "notice"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Count {

    @JsonProperty("total")
    public Integer total;

    @JsonProperty("error")
    public Integer error;

    @JsonProperty("warning")
    public Integer warning;

    @JsonProperty("notice")
    public Integer notice;

}
