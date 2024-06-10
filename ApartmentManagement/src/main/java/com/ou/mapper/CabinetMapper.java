package com.ou.mapper;

import com.ou.dto.response.CabinetResponse;
import com.ou.dto.response.ItemResponse;
import com.ou.pojo.Cabinet;
import com.ou.pojo.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // được quản lý boi spring theo DI
public interface CabinetMapper {
    List<CabinetResponse> toCabinetResponseList(List<Cabinet> cabinets);
    List<ItemResponse> toCabinetItemResponseList(List<Item> items);
}
