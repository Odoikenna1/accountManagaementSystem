package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.type.CategoryType;
import com.semicolon.africa.dtos.requests.AddItemRequest;
import com.semicolon.africa.dtos.response.AddItemResponse;
import com.semicolon.africa.services.Interfaces.ItemServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemServicesImplTest {
    @Autowired
    ItemServicesImpl itemServices;

    @Test
    public void testAddItemAndReturnAResponse() {
        AddItemRequest request = new AddItemRequest();
        request.setName("Test");
        request.setCategory("Electronics");
        request.setStock(15L);
        request.setInventoryId(1L);
        AddItemResponse response = itemServices.addItem(request);
        assertThat(response.getMessage()).isEqualTo("Item added successfully");
    }

}