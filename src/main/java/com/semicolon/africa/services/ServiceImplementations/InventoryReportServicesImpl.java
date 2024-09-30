package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.models.Inventory;
import com.semicolon.africa.dtos.requests.InventoryReportRequest;
import com.semicolon.africa.dtos.response.InventoryReportResponse;
import com.semicolon.africa.services.Interfaces.InventoryReportServices;

import java.util.ArrayList;
import java.util.List;

public class InventoryReportServicesImpl implements InventoryReportServices {

    private Inventory inventory;

    public InventoryReportServicesImpl(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public List<InventoryReportResponse> getAllInventoryReport(InventoryReportRequest request) {
        if (request == null || request.getUserId() == null) {
            throw new RuntimeException("Invalid request");
        }
        return new ArrayList<>();
    }

    @Override
    public void getInventoryReportByDate() {

    }

    @Override
    public void getInventoryReportByCategory() {

    }
}
