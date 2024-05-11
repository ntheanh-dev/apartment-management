package com.ou.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Service {
    private int id;
    private String name;
    private double price;
    private String note;
}
