package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.dtos.requests.AddTransactionRequest;
import com.semicolon.africa.dtos.response.AddTransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookKeepingServiceImplTest {

    @Autowired
    BookKeepingServiceImpl bookKeepingService;

    @Test
    public void testThatICanAddTransaction(){
        AddTransactionRequest request = new AddTransactionRequest();
        request.setSenderName("Eniola");
        request.setReceiverName("Alex");
        request.setTransactionType("DEBIT");
        request.setPaymentMethod("BANK_TRANSFER");
        AddTransactionResponse response = bookKeepingService.addTransaction(request);
        assertThat(response.getMessage()).isEqualTo("Transaction Added To Record");
    }

}