package com.MavenOrderCraft.MavenOrderCraft.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest extends BaseRequest{
    private UUID item_id;
    private int quantity;
}
