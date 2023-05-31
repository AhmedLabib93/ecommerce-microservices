package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.payload.request.ItemRequest;
import com.ecommerce.orderservice.payload.response.ItemResponse;
import com.ecommerce.orderservice.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ecommerce/v1/items")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest itemRequest) {
        return new ResponseEntity<ItemResponse>(itemService.saveItem(itemRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{item-id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable(name = "item-id") long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping("/{item-id}")
    public ResponseEntity<ItemResponse> updateItem(@Valid @RequestBody ItemRequest itemRequest,
                                                   @PathVariable(name = "item-id") long id) {
        return new ResponseEntity<ItemResponse>(
                itemService.updateItem(itemRequest, id),
                HttpStatus.CREATED);

    }

    @DeleteMapping("/{item-id}")
    public ResponseEntity<String> deleteItem(@PathVariable(name = "item-id") long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item removed successfully.");
    }
}
