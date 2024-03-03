package com.MavenOrderCraft.MavenOrderCraft.Service;

import com.MavenOrderCraft.MavenOrderCraft.DTO.InvoiceDTO;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Invoice;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.CardInfoMapper;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.InvoiceMapper;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.UserMapper;
import com.MavenOrderCraft.MavenOrderCraft.Repository.InvoiceRepository;
import com.MavenOrderCraft.MavenOrderCraft.Request.InvoiceRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponse;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponseList;
import com.MavenOrderCraft.MavenOrderCraft.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, UserService userService) {
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
    }

    @Transactional
    public InvoiceResponse saveInvoice(InvoiceRequest invoiceRequest){
        Invoice invoice = InvoiceMapper.INSTANCE.mapToItem(invoiceRequest);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return InvoiceMapper.INSTANCE.entityToResponse(savedInvoice);
    }

    @Transactional(readOnly = true)
    public InvoiceResponseList findByUser(UUID user_id){
        List<Invoice> invoiceList = invoiceRepository.findByUserId(user_id);
        InvoiceResponseList responseList = new InvoiceResponseList();
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for(int i=0; i<invoiceList.size(); i++){
            InvoiceDTO dto = InvoiceMapper.INSTANCE.mapToDTO(invoiceList.get(i));
            dto.setCardInfoResponse(CardInfoMapper.INSTANCE.entityToResponse(invoiceList.get(i).getCardInfo()));
            invoiceDTOS.add(dto);
        }
        responseList.setInvoiceResponseList(invoiceDTOS);
        return responseList;
    }

    @Transactional(readOnly = true)
    public InvoiceResponse findByID(UUID id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isEmpty()){
            throw new RuntimeException("Invoice not found with ID: " + id);
        }
        return InvoiceMapper.INSTANCE.entityToResponse(invoice.get());
    }

    @Transactional(readOnly = true)
    public InvoiceResponseList findAll() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        return toResponseList(invoiceList);
    }

    @Transactional(readOnly = true)
    public List<Invoice> getInvoicesInDateRange(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findByUserCreatedAtBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
    }

    @Transactional(readOnly = true)
    public InvoiceResponseList getInvoicesAboveAmount(BigDecimal amount) {
        List<Invoice> invoiceList = invoiceRepository.findByTotalAmountGreaterThan(amount);
        return toResponseList(invoiceList);
    }

    private InvoiceResponseList toResponseList(List<Invoice> invoiceList){
        InvoiceResponseList responseList = new InvoiceResponseList();
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for(int i=0; i<invoiceList.size(); i++){
            InvoiceDTO dto = InvoiceMapper.INSTANCE.mapToDTO(invoiceList.get(i));
            dto.setCardInfoResponse(CardInfoMapper.INSTANCE.entityToResponse(invoiceList.get(i).getCardInfo()));
            invoiceDTOS.add(dto);
        }
        responseList.setInvoiceResponseList(invoiceDTOS);
        return responseList;
    }

    public BigDecimal calculateAverageTotalAmountAboveAmount(BigDecimal amount) {
        List<Invoice> invoicesAboveAmount = invoiceRepository.findByTotalAmountGreaterThan(amount);

        if (invoicesAboveAmount.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal totalAmount = invoicesAboveAmount.stream()
                .map(Invoice::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averageTotalAmount = totalAmount.divide(BigDecimal.valueOf(invoicesAboveAmount.size()), 2, RoundingMode.HALF_UP);

        return averageTotalAmount;
    }

    public List<String> getCustomerNamesWithInvoicesBelowAmount(BigDecimal amount) {
        List<Invoice> invoicesBelowAmount = invoiceRepository.findByTotalAmountLessThan(amount);

        return invoicesBelowAmount.stream()
                .map(invoice -> invoice.getUser().getName())
                .distinct()
                .collect(Collectors.toList());
    }

}
