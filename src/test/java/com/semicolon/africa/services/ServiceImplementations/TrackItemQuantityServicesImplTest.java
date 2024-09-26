package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.dtos.requests.AddItemTrackRequest;
import com.semicolon.africa.dtos.response.AddItemTrackResponse;
import com.semicolon.africa.services.Interfaces.TrackItemQuantityServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.semicolon.africa.data.type.CategoryType.ELECTRONICS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrackItemQuantityServicesImplTest {

    @Autowired
    TrackItemQuantityServices trackItemQuantityServices;

    @Test
    public void testThatYouCanKeepTrackOfItemAndReceiveAResponse() {
        AddItemTrackRequest request = new AddItemTrackRequest();
        request.setItemId(1L);
        request.setName("Test");
        request.setStock(6L);
        request.setCategory(ELECTRONICS);
        AddItemTrackResponse response = trackItemQuantityServices.addAllItemUpdate(request);
        assertThat(response.getMessage()).isEqualTo("Added Update");
    }
}