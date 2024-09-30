package com.semicolon.africa.services.Interfaces;

import com.semicolon.africa.dtos.requests.InventoryReportRequest;
import com.semicolon.africa.dtos.response.InventoryReportResponse;

import java.util.List;

public interface InventoryReportServices {
    List<InventoryReportResponse> getAllInventoryReport(InventoryReportRequest request);
    void getInventoryReportByDate();
    void getInventoryReportByCategory();
}
