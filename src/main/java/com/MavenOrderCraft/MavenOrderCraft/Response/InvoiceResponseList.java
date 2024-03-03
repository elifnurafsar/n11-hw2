package com.MavenOrderCraft.MavenOrderCraft.Response;

import com.MavenOrderCraft.MavenOrderCraft.DTO.InvoiceDTO;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceResponseList {
    private List<InvoiceDTO> invoiceResponseList;
}
