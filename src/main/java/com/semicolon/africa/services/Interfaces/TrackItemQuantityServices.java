package com.semicolon.africa.services.Interfaces;

import com.semicolon.africa.dtos.requests.AddItemTrackRequest;
import com.semicolon.africa.dtos.response.AddItemTrackResponse;

public interface TrackItemQuantityServices {

    AddItemTrackResponse addAllItemUpdate(AddItemTrackRequest request);
}
