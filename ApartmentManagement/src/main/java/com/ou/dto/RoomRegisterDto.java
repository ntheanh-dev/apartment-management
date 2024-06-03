package com.ou.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class RoomRegisterDto {
    private int name;
    private LocalDate dob;
    private String city;
    private String ward;
    private String address;
    private Boolean gender;
    private String identity;
    private String phoneNumber;
    private String email;
    private String number_plate;
    private long securityDeposit;
    private LocalDate startedDate;
    private LocalDate endedDate;
}
