package com.ou.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentResponse {
    Integer id;
    String fullName;
    Boolean gender;
    String avatar;
    String phone;
    String email;
    String numberPlate;
    String city;
    String ward;
    String address;
    String dateOfBirth;
    String identity;
}
