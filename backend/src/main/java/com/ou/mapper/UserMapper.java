package com.ou.mapper;

import com.ou.dto.RoomRegisterDto;
import com.ou.dto.request.UserCreationRequest;
import com.ou.dto.response.UserResponse;
import com.ou.pojo.Contract;
import com.ou.pojo.Resident;
import com.ou.pojo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // được quản lý boi spring theo DI
public interface UserMapper { // Mapstruct sẽ tự tạo ra một class có tên UserMapperImpl là tự override
    User toUser(UserCreationRequest request);
    User toUser(RoomRegisterDto user);
    UserResponse toUserResponse(User user);
    Contract toContract(RoomRegisterDto user);
    Resident toResident(RoomRegisterDto user);
}
