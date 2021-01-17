package com.omernaci.mawaccess.common.dto;

import com.omernaci.mawaccess.common.model.Count;
import com.omernaci.mawaccess.common.model.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

    public String id;

    public String task;

    public String date;

    public Count count;

    public List<Object> ignore = new ArrayList<>();

    public List<Result> results = new ArrayList<>();

}
