package com.MavenOrderCraft.MavenOrderCraft.Controller;

import com.MavenOrderCraft.MavenOrderCraft.Request.CreateInvoiceRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponse;
import com.MavenOrderCraft.MavenOrderCraft.Service.OrderProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-process")
public class OrderProcessController {
    private final OrderProcessService orderProcessService;

    @Autowired
    public OrderProcessController(OrderProcessService orderProcessService) {
        this.orderProcessService = orderProcessService;
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> create(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
        InvoiceResponse invoiceResponse = orderProcessService.processOrder(createInvoiceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceResponse);
    }
}

