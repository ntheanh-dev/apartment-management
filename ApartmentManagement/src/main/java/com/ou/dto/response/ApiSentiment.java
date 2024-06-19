package com.ou.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiSentiment {
    private String result;

    public ApiSentiment(@JsonProperty("result") String result) {
        this.result = result;
    }
}
