package com.omernaci.mawaccess.common.response;

import com.omernaci.mawaccess.common.xml.Resultset;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ACheckerResponse extends BaseApiResponse {

    private Resultset resultset;

}
