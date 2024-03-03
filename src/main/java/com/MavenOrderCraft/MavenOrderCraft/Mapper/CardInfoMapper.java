package com.MavenOrderCraft.MavenOrderCraft.Mapper;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.CardInfo;
import com.MavenOrderCraft.MavenOrderCraft.Request.CardInfoRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.CardInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CardInfoMapper {
    CardInfoMapper INSTANCE = Mappers.getMapper(CardInfoMapper.class);

    CardInfo requestToEntity(CardInfoRequest cardInfoRequest);

    CardInfoResponse entityToResponse(CardInfo cardInfo);

    List<CardInfoResponse> entityListToResponseList(List<CardInfo> cardInfoList);
}