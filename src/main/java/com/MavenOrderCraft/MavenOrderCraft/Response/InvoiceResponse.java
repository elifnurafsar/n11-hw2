package com.MavenOrderCraft.MavenOrderCraft.Response;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private UUID id;
    private User user;
    private LocalDateTime createdAt;
    private List<OrderResponse> orders;
    private BigDecimal totalAmount;
}

