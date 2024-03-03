package com.MavenOrderCraft.MavenOrderCraft.Request;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.CardInfo;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Order;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceRequest extends BaseRequest{
    private User user;
    private CardInfo cardInfo;
    private LocalDateTime createdAt;
    private List<Order> orders;
    private BigDecimal totalAmount;
}
