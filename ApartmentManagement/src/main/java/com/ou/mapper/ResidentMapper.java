package com.ou.mapper;

import com.ou.dto.response.ResidentResponse;
import com.ou.pojo.Resident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResidentMapper {
    ResidentResponse toResidentResponse(Resident resident);
}
