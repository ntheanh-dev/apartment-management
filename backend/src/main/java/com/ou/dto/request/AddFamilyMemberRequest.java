package com.ou.dto.request;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddFamilyMemberRequest {
    String name;
    LocalDate dob;
    String relationshipType;
}
