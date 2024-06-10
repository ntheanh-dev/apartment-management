package com.ou.mapper;

import com.ou.dto.request.ItemCreationRequest;
import com.ou.pojo.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // được quản lý boi spring theo DI
public interface ItemMapper {
    Item toItem(ItemCreationRequest item);
}
