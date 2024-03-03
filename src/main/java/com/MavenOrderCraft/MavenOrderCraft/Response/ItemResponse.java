package com.MavenOrderCraft.MavenOrderCraft.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
}
