package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.entity.Item;
import com.ecommerce.orderservice.exception.ResourceNotFound;
import com.ecommerce.orderservice.payload.request.ItemRequest;
import com.ecommerce.orderservice.payload.response.ItemResponse;
import com.ecommerce.orderservice.repository.ItemRepository;
import com.ecommerce.orderservice.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemResponse saveItem(ItemRequest itemRequest) {
        Item saved = itemRepository.save(modelMapper.map(itemRequest, Item.class));
        return modelMapper.map(saved, ItemResponse.class);
    }

    @Override
    public ItemResponse updateItem(ItemRequest itemRequest, long id) {
        Item item = itemRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Item not found with id " + id));

        item.setId(id);
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());

        Item updated = itemRepository.save(item);
        return modelMapper.map(updated, ItemResponse.class);
    }

    @Override
    public void deleteItem(long id) {
        Item item = itemRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Item not found with id " + id));
        itemRepository.delete(item);
    }

    @Override
    public ItemResponse getItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Item not found with id " + id));
        return modelMapper.map(item, ItemResponse.class);
    }


    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll().stream().map((item)
                -> modelMapper.map(item, ItemResponse.class)).collect(Collectors.toList());
    }
}
