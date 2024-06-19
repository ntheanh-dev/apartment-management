package com.ou.dto.request;


import com.ou.pojo.DetailEvoluation;
import com.ou.pojo.Evaluation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormEvaluation {
    private Evaluation evaluation;
    private List<DetailEvaluationFrom> detailEvaluations;
}
