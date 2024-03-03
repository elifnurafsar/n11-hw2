package com.MavenOrderCraft.MavenOrderCraft.DTO;

import com.MavenOrderCraft.MavenOrderCraft.Response.CardInfoResponse;
import com.MavenOrderCraft.MavenOrderCraft.Response.OrderResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class InvoiceDTO {
    private UUID id;
    private String user;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private List<OrderResponse> orders;
    private CardInfoResponse cardInfoResponse;
}
