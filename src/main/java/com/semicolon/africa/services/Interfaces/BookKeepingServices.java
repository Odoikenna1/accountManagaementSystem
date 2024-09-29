package com.semicolon.africa.services.Interfaces;

import com.semicolon.africa.dtos.requests.AddTransactionRequest;
import com.semicolon.africa.dtos.response.AddTransactionResponse;

public interface BookKeepingServices {
    AddTransactionResponse addTransaction(AddTransactionRequest request);
}
