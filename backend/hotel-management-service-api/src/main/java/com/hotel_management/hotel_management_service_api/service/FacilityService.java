package com.hotel_management.hotel_management_service_api.service;

import com.hotel_management.hotel_management_service_api.dto.request.RequestFacilityDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseFacilityDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseFacilityPaginationDto;

public interface FacilityService {

    public void createFacility(RequestFacilityDto data);
    public void updateFacility(RequestFacilityDto data, String facilityId);
    public void deleteFacility(String facilityId);
    public ResponseFacilityDto findFacilityByFacilityId(String facilityId);
    public ResponseFacilityPaginationDto findRoomsByFacilityId(int page, int size, String facilityId);
    public ResponseFacilityPaginationDto findRoomsByFacilityName(int page, int size, String facilityName);
    public ResponseFacilityPaginationDto findAllFacilities(int page, int size, String searchText);
}
