package com.MavenOrderCraft.MavenOrderCraft.Mapper;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import com.MavenOrderCraft.MavenOrderCraft.Request.UserRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestToEntity(UserRequest userRequest);

    User responseToEntity(UserResponse userResponse);

    UserResponse entityToResponse(User user);

    List<UserResponse> entityListToResponseList(List<User> users);
}
