package com.ou.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class Room {
    private int roomId;
    private String roomName;
    private String location;
    private int price;
    private int length;
    private int width;
    private int capacity;
    private String description;
    private String male;
}
