package com.hotel_management.hotel_management_service_api.service;

import com.hotel_management.hotel_management_service_api.dto.request.RequestAddressDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseAddressDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseAddressPaginationDto;

public interface AddressService {

    public void createAddress(RequestAddressDto data);
    public void updateAddress(RequestAddressDto data, String addressId);
    public void deleteAddress(String addressId);
    public ResponseAddressDto findById(String addressId);
    public ResponseAddressDto findByBranchId(String branchId);
    public ResponseAddressPaginationDto findAddressByCity(int page, int size, String city);
}
