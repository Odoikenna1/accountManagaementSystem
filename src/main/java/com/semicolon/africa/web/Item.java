package com.semicolon.africa.web;

import com.semicolon.africa.services.ServiceImplementations.ItemServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item/")
public class Item {

    private final ItemServicesImpl itemServices;


    public Item(ItemServicesImpl itemServices) {
        this.itemServices = itemServices;
    }

    @PostMapping
}
