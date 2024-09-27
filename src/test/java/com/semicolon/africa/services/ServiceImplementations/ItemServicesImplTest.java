package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.repositories.ItemRepository;
import com.semicolon.africa.data.type.CategoryType;
import com.semicolon.africa.dtos.requests.AddItemRequest;
import com.semicolon.africa.dtos.requests.RemoveItemRequest;
import com.semicolon.africa.dtos.response.AddItemResponse;
import com.semicolon.africa.dtos.response.RemoveItemResponse;
import com.semicolon.africa.exception.ItemException;
import com.semicolon.africa.services.Interfaces.ItemServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemServicesImplTest {
    @Autowired
    ItemServicesImpl itemServices;

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemRepository.deleteAll();
    }

    @Test
    public void testAddItemAndReturnAResponse() {
        AddItemRequest request = new AddItemRequest();
        request.setName("Test");
        request.setCategory("ELECTRONICS");
        request.setStock(15L);
        request.setInventoryId(1L);
        request.setUserId(5L);
        AddItemResponse response = itemServices.addItem(request);
        assertThat(response.getMessage()).isEqualTo("Item added successfully");
    }

    @Test
    public void testThatICanAddItemAndReturnAResponseAndAddThatSameItemAndStockIncrease() {
        AddItemRequest request = new AddItemRequest();
        request.setName("Test");
        request.setCategory("ELECTRONICS");
        request.setStock(15L);
        request.setInventoryId(1L);
        request.setUserId(5L);
        AddItemResponse response = itemServices.addItem(request);
        assertThat(response.getMessage()).isEqualTo("Item added successfully");
        AddItemRequest request1 = new AddItemRequest();
        request1.setName("Test");
        request1.setCategory("ELECTRONICS");
        request1.setStock(5L);
        request1.setInventoryId(1L);
        request1.setUserId(5L);
        AddItemResponse response2 = itemServices.addItem(request1);
        assertThat(response2.getStock()).isEqualTo(20);
    }

    @Test
    public void testThatICanAddItemAndReturnAResponseAndRemoveThatSameItemStockAndStockDecrease() {
        AddItemRequest request = new AddItemRequest();
        request.setName("Test");
        request.setCategory("ELECTRONICS");
        request.setStock(15L);
        request.setInventoryId(1L);
        request.setUserId(5L);
        AddItemResponse response = itemServices.addItem(request);
        assertThat(response.getMessage()).isEqualTo("Item added successfully");

        RemoveItemRequest removeItemRequest = new RemoveItemRequest();
        removeItemRequest.setName("Test");
        removeItemRequest.setCategory("ELECTRONICS");
        removeItemRequest.setStock(10L);
        removeItemRequest.setInventoryId(1L);
        removeItemRequest.setUserId(5L);
        RemoveItemResponse removeItemResponse = itemServices.removeItem(removeItemRequest);
        assertThat(removeItemResponse.getStock()).isEqualTo(5);
    }

    @Test
    public void testThatIfYouTryToRemoveMoreStockThanTheOneInTheStoreThrowException() {
        AddItemRequest request = new AddItemRequest();
        request.setName("Test");
        request.setCategory("ELECTRONICS");
        request.setStock(15L);
        request.setInventoryId(1L);
        request.setUserId(5L);
        AddItemResponse response = itemServices.addItem(request);
        assertThat(response.getMessage()).isEqualTo("Item added successfully");

        RemoveItemRequest removeItemRequest = new RemoveItemRequest();
        removeItemRequest.setName("Test");
        removeItemRequest.setCategory("ELECTRONICS");
        removeItemRequest.setStock(30L);
        removeItemRequest.setInventoryId(1L);
        removeItemRequest.setUserId(5L);
        assertThrows(ItemException.class,()->itemServices.removeItem(removeItemRequest));
    }
}