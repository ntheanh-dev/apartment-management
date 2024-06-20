package com.ou.dto.request;

import com.ou.pojo.Cabinet;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreationRequest {
    String name;
    String description;
    LocalDate deliveryDate;
    Cabinet cabinet;
    MultipartFile file;
}
