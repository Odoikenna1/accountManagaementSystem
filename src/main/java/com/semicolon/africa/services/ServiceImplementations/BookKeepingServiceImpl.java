package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.models.BookKeeping;
import com.semicolon.africa.data.repositories.BookKeepingRepo;
import com.semicolon.africa.dtos.requests.AddTransactionRequest;
import com.semicolon.africa.dtos.response.AddTransactionResponse;
import com.semicolon.africa.exception.BookKeepingException;
import com.semicolon.africa.services.Interfaces.BookKeepingServices;
import com.semicolon.africa.utilities.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BookKeepingServiceImpl implements BookKeepingServices {

    @Autowired
    BookKeepingRepo bookKeepingRepo;

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
    public AddTransactionResponse addTransaction(AddTransactionRequest request) {
        validateInputAreNotEmpty(request.getReceiverName());
        validateInputAreNotEmpty(request.getSenderName());
        BookKeeping bookKeeping = new BookKeeping(generateYear(),generateMonth(),generateDay(),generateTime());
        Mapper.map(bookKeeping,request);
        bookKeepingRepo.save(bookKeeping);
        AddTransactionResponse response = new AddTransactionResponse();
        Mapper.map(bookKeeping,response);
        return response;
    }

    private void validateInputAreNotEmpty(String details) {
        if(details == null || details.isEmpty())throw new BookKeepingException("Put a valid input");
    }
}
