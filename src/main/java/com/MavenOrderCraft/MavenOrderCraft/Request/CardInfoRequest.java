package com.MavenOrderCraft.MavenOrderCraft.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoRequest extends BaseRequest{
    private String cardNumber;
    private String cardHolderName;
    private int cvv2;
}

