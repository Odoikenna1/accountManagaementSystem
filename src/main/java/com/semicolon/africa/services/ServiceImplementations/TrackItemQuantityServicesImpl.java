package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.models.TrackItemQuantity;
import com.semicolon.africa.data.repositories.TrackItemQuantityRepository;
import com.semicolon.africa.dtos.requests.AddItemTrackRequest;
import com.semicolon.africa.dtos.response.AddItemTrackResponse;
import com.semicolon.africa.services.Interfaces.TrackItemQuantityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TrackItemQuantityServicesImpl implements TrackItemQuantityServices {
    @Autowired
    private TrackItemQuantityRepository trackItemQuantityRepository;

    private LocalDateTime generateLocalDate(){
        return  LocalDateTime.now();
    }
     private int generateYear(){
        return generateLocalDate().getYear();
     }

     private int generateMonth(){
        return generateLocalDate().getMonthValue();
     }

     private int generateDay(){
        return generateLocalDate().getDayOfMonth();
     }

     private String generateTime(){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
         LocalDateTime now = generateLocalDate();
         return now.format(formatter);
     }

    @Override
    public AddItemTrackResponse addAllItemUpdate(AddItemTrackRequest request) {
        TrackItemQuantity trackItemQuantity = new TrackItemQuantity(generateYear(), generateMonth(), generateDay(), generateTime());
        trackItemQuantity.setItemId(request.getItemId());
        trackItemQuantity.setName(request.getName());
        trackItemQuantity.setStock(request.getStock());
        trackItemQuantity.setCategory(request.getCategory());
        trackItemQuantityRepository.save(trackItemQuantity);
        AddItemTrackResponse response = new AddItemTrackResponse();
        response.setName(trackItemQuantity.getName());
        response.setStock(trackItemQuantity.getStock());
        response.setCategory(trackItemQuantity.getCategory());
        response.setMessage("Added Update");
        return response;
    }
}
