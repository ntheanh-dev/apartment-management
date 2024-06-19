package com.ou.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailEvaluationFrom {
    private int idCriterion;
    private String rate;
}
