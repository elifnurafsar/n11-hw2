package com.MavenOrderCraft.MavenOrderCraft.Controller;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Invoice;
import com.MavenOrderCraft.MavenOrderCraft.Request.InvoiceRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponse;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponseList;
import com.MavenOrderCraft.MavenOrderCraft.Response.ItemResponse;
import com.MavenOrderCraft.MavenOrderCraft.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Invoices")
public class InvoiceController implements BaseController<InvoiceRequest> {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(InvoiceRequest request) {
        InvoiceResponse invoiceResponse = invoiceService.saveInvoice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceResponse);
    }

    @Override
    @GetMapping("/find-by-id")
    public ResponseEntity<?> getById(@RequestParam("id") UUID id) {
        InvoiceResponse invoiceResponse = invoiceService.findByID(id);
        return ResponseEntity.ok(invoiceResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<InvoiceResponseList> getAll() {
        InvoiceResponseList invoiceResponses = invoiceService.findAll();
        return ResponseEntity.ok(invoiceResponses);
    }

    @GetMapping("/find-by-user")
    public ResponseEntity<InvoiceResponseList>  getByUser(@RequestParam("user_id") UUID user_id) {
        InvoiceResponseList invoiceResponses = invoiceService.findByUser(user_id);
        return ResponseEntity.ok(invoiceResponses);
    }

    @GetMapping("/total-amount-in-given-month")
    public Map<String, BigDecimal> getTotalAmountInJune() {
        LocalDate startOfJune = LocalDate.of(2023, 6, 1);
        LocalDate endOfJune = LocalDate.of(2023, 6, 30);

        List<Invoice> invoicesInJune = invoiceService.getInvoicesInDateRange(startOfJune, endOfJune);
        Map<String, BigDecimal> totalAmountByUser = invoicesInJune.stream()
                .collect(Collectors.groupingBy(
                        invoice -> invoice.getUser().getName(),
                        Collectors.reducing(BigDecimal.ZERO, Invoice::getTotalAmount, BigDecimal::add)
                ));

        return totalAmountByUser;
    }

    @GetMapping("/find-by-total-amount-greater-than")
    public ResponseEntity<InvoiceResponseList> findByTotalAmountGreaterThan(@RequestParam("amount") BigDecimal amount) {
        InvoiceResponseList invoiceResponses = invoiceService.getInvoicesAboveAmount(amount);
        return ResponseEntity.ok(invoiceResponses);
    }

    @GetMapping("/get-average-by-total-amount-greater-than")
    public ResponseEntity<BigDecimal> getAverageByTotalAmountGreaterThan(@RequestParam("amount") BigDecimal amount) {
        BigDecimal res = invoiceService.calculateAverageTotalAmountAboveAmount(amount);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-names-by-total-amount-less-than")
    public ResponseEntity<List<String>> getCustomerNamesWithInvoicesBelowAmount(@RequestParam("amount") BigDecimal amount) {
        List<String> res = invoiceService.getCustomerNamesWithInvoicesBelowAmount(amount);
        return ResponseEntity.ok(res);
    }
}
