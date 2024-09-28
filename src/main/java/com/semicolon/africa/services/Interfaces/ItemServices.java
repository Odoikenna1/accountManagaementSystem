package com.semicolon.africa.services.Interfaces;

import com.semicolon.africa.dtos.requests.AddItemRequest;
import com.semicolon.africa.dtos.requests.RemoveItemRequest;
import com.semicolon.africa.dtos.response.AddItemResponse;
import com.semicolon.africa.dtos.response.RemoveItemResponse;

public interface ItemServices {
    AddItemResponse addItem(AddItemRequest request);

    RemoveItemResponse removeItem(RemoveItemRequest request);
}
