package com.ou.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CabinetResponse {
    Integer id;
    Boolean isActive;
    Integer contractId;
    Integer unreceivedItemCount;
}
