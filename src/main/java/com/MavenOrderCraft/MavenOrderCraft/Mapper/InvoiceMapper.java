package com.MavenOrderCraft.MavenOrderCraft.Mapper;

import com.MavenOrderCraft.MavenOrderCraft.DTO.InvoiceDTO;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.CardInfo;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Invoice;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import com.MavenOrderCraft.MavenOrderCraft.Request.InvoiceRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    Invoice mapToItem(InvoiceRequest invoiceRequest);

    InvoiceDTO mapToDTO(Invoice invoice);

    InvoiceResponse entityToResponse(Invoice invoice);

    List<InvoiceResponse> entityListToResponseList(List<Invoice> invoices);

    static String map(User user) {
        return user.getEmail();
    }
}
