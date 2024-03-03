package com.MavenOrderCraft.MavenOrderCraft.Mapper;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Item;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Order;
import com.MavenOrderCraft.MavenOrderCraft.Request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order requestToEntity(OrderRequest orderRequest);

    List<Order> requestListToEntityList(List<OrderRequest> orderRequestList);

}