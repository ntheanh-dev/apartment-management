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
    private String name;
    private String description;
    private LocalDate deliveryDate;
    private Cabinet cabinet;
    private MultipartFile file;
}
