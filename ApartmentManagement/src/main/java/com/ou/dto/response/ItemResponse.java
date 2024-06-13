package com.ou.dto.response;

import com.ou.pojo.Cabinet;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponse {
    Integer id;
    String name;
    String description;
    LocalDate deliveryDate;
    String image;
    LocalDate receivedDate;

}
