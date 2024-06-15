package com.ou.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FamilyMemberResponse {
    Integer id;
    String name;
    LocalDate dob;
    LocalDate createdAt;
    String relationshipType;
}
