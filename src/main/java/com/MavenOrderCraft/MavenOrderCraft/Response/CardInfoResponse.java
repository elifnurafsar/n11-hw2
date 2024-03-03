package com.MavenOrderCraft.MavenOrderCraft.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoResponse {
    private UUID id;
    private String cardNumber;
    private String cardHolderName;
}

