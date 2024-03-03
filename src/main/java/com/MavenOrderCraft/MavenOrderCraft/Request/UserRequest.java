package com.MavenOrderCraft.MavenOrderCraft.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends BaseRequest{
    private String name;
    private String email;
    private String address;
}

