package com.MavenOrderCraft.MavenOrderCraft.Controller;

import com.MavenOrderCraft.MavenOrderCraft.Request.BaseRequest;
import com.MavenOrderCraft.MavenOrderCraft.Request.ItemRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.ItemResponse;
import com.MavenOrderCraft.MavenOrderCraft.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Items")
public class ItemController implements BaseController<ItemRequest>{
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.createItem(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponse);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ItemResponse> getById(@RequestParam("id") UUID id) {
        ItemResponse itemResponse = itemService.getItemById(id);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAll() {
        List<ItemResponse> itemResponses = itemService.getAllItems();
        return ResponseEntity.ok(itemResponses);
    }
}
