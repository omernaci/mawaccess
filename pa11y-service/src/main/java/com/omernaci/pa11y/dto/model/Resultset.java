package com.omernaci.pa11y.dto.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "task",
        "date",
        "count",
        "ignore",
        "results"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resultset {

    @JsonProperty("id")
    public String id;

    @JsonProperty("task")
    public String task;

    @JsonProperty("date")
    public String date;

    @JsonProperty("count")
    public Count count;

    @JsonProperty("ignore")
    public List<Object> ignore = new ArrayList<>();

    @JsonProperty("results")
    public List<Result> results = new ArrayList<>();

}
