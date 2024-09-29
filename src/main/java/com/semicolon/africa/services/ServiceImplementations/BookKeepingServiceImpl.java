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

@Service
public class BookKeepingServiceImpl implements BookKeepingServices {

    @Autowired
    BookKeepingRepo bookKeepingRepo;

    @Override
    public AddTransactionResponse addTransaction(AddTransactionRequest request) {
        validateInputAreNotEmpty(request.getReceiverName());
        validateInputAreNotEmpty(request.getSenderName());
        BookKeeping bookKeeping = new BookKeeping();
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
