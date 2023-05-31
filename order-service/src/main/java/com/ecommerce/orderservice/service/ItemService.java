package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.payload.request.ItemRequest;
import com.ecommerce.orderservice.payload.response.ItemResponse;

import java.util.List;

public interface ItemService {


    ItemResponse saveItem(ItemRequest itemRequest);


    ItemResponse getItemById(long id);

    List<ItemResponse> getAllItems();

    ItemResponse updateItem(ItemRequest itemRequest, long id);

    void deleteItem(long id);
}
