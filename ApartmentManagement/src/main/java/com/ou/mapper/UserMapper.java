package com.ou.mapper;

import com.ou.dto.request.UserCreationRequest;
import com.ou.pojo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // được quản lý boi spring theo DI
public interface UserMapper { // Mapstruct sẽ tự tạo ra một class có tên UserMapperImpl là tự override
    User toUser(UserCreationRequest request);
}
