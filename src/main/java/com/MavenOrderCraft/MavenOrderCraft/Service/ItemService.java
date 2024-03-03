package com.MavenOrderCraft.MavenOrderCraft.Service;


import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Item;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.ItemMapper;
import com.MavenOrderCraft.MavenOrderCraft.Repository.ItemRepository;
import com.MavenOrderCraft.MavenOrderCraft.Request.ItemRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public ItemResponse createItem(ItemRequest itemRequest) {
        Item item = ItemMapper.INSTANCE.mapToItem(itemRequest);
        Item savedItem = itemRepository.save(item);
        return  ItemMapper.INSTANCE.entityToResponse(savedItem);
    }

    @Transactional(readOnly = true)
    public ItemResponse getItemById(UUID itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        return ItemMapper.INSTANCE.entityToResponse(item);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return ItemMapper.INSTANCE.entityListToResponseList(items);
    }

}
