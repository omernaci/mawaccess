package com.omernaci.mawaccess.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    public String id;

    public String name;

    public String url;

    public Integer timeout;

    public Integer wait;

    public String standard;

    public List<Object> ignore = null;

    public List<Object> actions = null;

}
