package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.models.Item;
import com.semicolon.africa.data.repositories.ItemRepository;
import com.semicolon.africa.data.type.CategoryType;
import com.semicolon.africa.dtos.requests.AddItemRequest;
import com.semicolon.africa.dtos.requests.RemoveItemRequest;
import com.semicolon.africa.dtos.response.AddItemResponse;
import com.semicolon.africa.dtos.response.RemoveItemResponse;
import com.semicolon.africa.exception.ItemException;
import com.semicolon.africa.services.Interfaces.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServicesImpl implements ItemServices {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public AddItemResponse addItem(AddItemRequest request) {
        Item newItem = new Item();
        if(findItemByName(request.getName()) && findItemByCategory(request.getCategory())){
            CategoryType categoryType = CategoryType.valueOf(request.getCategory());
            Item item = itemRepository.getItemsByNameAndCategory(request.getName(),categoryType)
                    .orElseThrow(()->new ItemException("item not found"));
            item.setStock(request.getStock()+item.getStock());
            AddItemResponse addItemResponse = new AddItemResponse();
            addItemResponse.setItemId(item.getId());
            addItemResponse.setItemName(item.getName());
            addItemResponse.setCategoryType(item.getCategory());
            addItemResponse.setStock(item.getStock());
            return addItemResponse;
        }
        newItem.setName(request.getName());
        CategoryType categoryType = CategoryType.valueOf(request.getCategory());
        newItem.setCategory(categoryType);
        newItem.setStock(request.getStock());
        newItem.setInventoryId(request.getInventoryId());
        AddItemResponse addItemResponse = new AddItemResponse();
        addItemResponse.setItemId(newItem.getId());
        addItemResponse.setItemName(newItem.getName());
        addItemResponse.setCategoryType(newItem.getCategory());
        addItemResponse.setStock(newItem.getStock());

        return addItemResponse;
    }

    private boolean findItemByCategory(String category) {
        CategoryType newCategory=  CategoryType.valueOf(category.toUpperCase());
        return itemRepository.getItemsByCategory(newCategory);
    }

    private boolean findItemByName(String name) {

        return itemRepository.getItemsByName(name);
    }

    @Override
    public RemoveItemResponse removeItem(RemoveItemRequest request) {
        return null;
    }
}
