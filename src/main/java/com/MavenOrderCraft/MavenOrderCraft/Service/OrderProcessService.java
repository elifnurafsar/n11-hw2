package com.MavenOrderCraft.MavenOrderCraft.Service;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.CardInfo;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Item;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Order;
import com.MavenOrderCraft.MavenOrderCraft.Request.CardInfoRequest;
import com.MavenOrderCraft.MavenOrderCraft.Request.CreateInvoiceRequest;
import com.MavenOrderCraft.MavenOrderCraft.Request.InvoiceRequest;
import com.MavenOrderCraft.MavenOrderCraft.Request.OrderRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.CardInfoResponse;
import com.MavenOrderCraft.MavenOrderCraft.Response.InvoiceResponse;
import com.MavenOrderCraft.MavenOrderCraft.Response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderProcessService {
    private final CardInfoService cardInfoService;
    private final OrderService orderService;
    private final InvoiceService invoiceService;
    private final ItemService itemService;

    @Autowired
    public OrderProcessService(CardInfoService cardInfoService, OrderService orderService, InvoiceService invoiceService, ItemService itemService) {
        this.cardInfoService = cardInfoService;
        this.orderService = orderService;
        this.invoiceService = invoiceService;
        this.itemService = itemService;
    }

    @Transactional
    public InvoiceResponse processOrder(CreateInvoiceRequest createInvoiceRequest) {
        List<OrderRequest> orderRequestList = createInvoiceRequest.getOrders();
        CardInfoRequest cardInfoRequest = createInvoiceRequest.getCardInfo();
        List<Order> orders = orderService.saveAll(orderRequestList);
        CardInfo cardInfo= cardInfoService.createCardInfo(cardInfoRequest);
        InvoiceRequest request = new InvoiceRequest();
        request.setOrders(orders);
        request.setCardInfo(cardInfo);
        request.setUser(createInvoiceRequest.getUser());
        request.setCreatedAt(LocalDateTime.now());
        BigDecimal total = findTotal(orders);
        request.setTotalAmount(total);
        return invoiceService.saveInvoice(request);
    }

    private BigDecimal findTotal(List<Order> orders) {
        BigDecimal sum = BigDecimal.ZERO;
        for(int i=0; i<orders.size(); i++){
            Order o = orders.get(i);
            ItemResponse item= itemService.getItemById(o.getItem_id());
            BigDecimal price = item.getPrice();
            int quantity = o.getQuantity();
            BigDecimal itemCost  = price.multiply(BigDecimal.valueOf(quantity));
            sum = sum.add(itemCost);
        }
        return sum;
    }
}
