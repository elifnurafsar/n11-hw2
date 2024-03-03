package com.MavenOrderCraft.MavenOrderCraft.Mapper;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Item;
import com.MavenOrderCraft.MavenOrderCraft.Request.ItemRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item mapToItem(ItemRequest itemRequest);

    Item mapFromResponseToItem(ItemResponse itemResponse);

    ItemResponse entityToResponse(Item item);

    List<ItemResponse> entityListToResponseList(List<Item> items);
}
