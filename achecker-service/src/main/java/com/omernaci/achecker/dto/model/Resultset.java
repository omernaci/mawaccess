package com.omernaci.achecker.dto.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="resultset")
public class Resultset {

    private Summary summary;

    private Results results;

}
