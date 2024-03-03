package com.MavenOrderCraft.MavenOrderCraft.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest extends BaseRequest{
    private String name;
    private BigDecimal price;
}
