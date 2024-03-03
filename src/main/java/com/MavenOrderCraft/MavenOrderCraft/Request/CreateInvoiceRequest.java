package com.MavenOrderCraft.MavenOrderCraft.Request;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest extends BaseRequest{
    @NotNull(message = "User cannot be null")
    private User user;

    @Valid
    @NotNull(message = "Orders cannot be null")
    private List<OrderRequest> orders;

    @Valid
    @NotNull(message = "Card information cannot be null")
    private CardInfoRequest cardInfo;
}
