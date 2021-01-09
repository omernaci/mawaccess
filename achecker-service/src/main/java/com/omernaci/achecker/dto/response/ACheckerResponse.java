package com.omernaci.achecker.dto.response;

import com.omernaci.achecker.dto.xml.Resultset;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ACheckerResponse extends BaseApiResponse {

    private Resultset resultset;

}
