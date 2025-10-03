package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestAddressDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseAddressDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseAddressPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.Address;
import com.hotel_management.hotel_management_service_api.entity.Branch;
import com.hotel_management.hotel_management_service_api.repository.AddressRepository;
import com.hotel_management.hotel_management_service_api.repository.BranchRepository;
import com.hotel_management.hotel_management_service_api.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final BranchRepository branchRepository;

    @Override
    public void createAddress(RequestAddressDto data) {
        addressRepository.save(toAddress(data));
    }

    @Override
    public void updateAddress(RequestAddressDto data, String addressId) {
        Address selectedAddress = addressRepository.findById(addressId).orElseThrow(
                () -> new RuntimeException("Address not found with id: " + addressId)
        );
        selectedAddress.setAddressLine(data.getAddressLine());
        selectedAddress.setCity(data.getCity());
        selectedAddress.setCountry(data.getCountry());
        selectedAddress.setLongitude(data.getLongitude());
        selectedAddress.setLatitude(data.getLatitude());
        selectedAddress.setBranch(findBranchById(data.getBranchId()));
        addressRepository.save(selectedAddress);
    }

    @Override
    public void deleteAddress(String addressId) {
        Address selectedAddress = addressRepository.findById(addressId).orElseThrow(
                () -> new RuntimeException("Address not found with id: " + addressId)
        );
        addressRepository.delete(selectedAddress);
    }

    @Override
    public ResponseAddressDto findById(String addressId) {
        Address selectedAddress = addressRepository.findById(addressId).orElseThrow(
                () -> new RuntimeException("Address not found with id: " + addressId)
        );
        return toResponseAddressDto(selectedAddress);
    }

    @Override
    public ResponseAddressDto findByBranchId(String branchId) {
        Address addressByBranchId = addressRepository.findAddressByBranchId(branchId);
        return addressByBranchId != null ? toResponseAddressDto(addressByBranchId) : null;
    }

    @Override
    public ResponseAddressPaginationDto findAddressByCity(int page, int size, String city) {
        return ResponseAddressPaginationDto.builder()
                .dataCount(addressRepository.countSearchAddresses(city))
                .dataList(
                        addressRepository.searchAllAddresses(city, PageRequest.of(page,size))
                                .stream().map(this::toResponseAddressDto).toList()
                )
                .build();
    }

    // dto mapped to the entity
    private Address toAddress(RequestAddressDto data){
        return data == null ? null :
                Address.builder()
                        .addressLine(data.getAddressLine())
                        .city(data.getCity())
                        .country(data.getCountry())
                        .longitude(data.getLongitude())
                        .latitude(data.getLatitude())
                        .branch(findBranchById(data.getBranchId()))
                        .build();
    }

    // find branch related to the hotel
    private Branch findBranchById(String branchId){
        return branchRepository.findById(branchId).orElse(null);
    }

    // response address (entity to dto)
    private ResponseAddressDto toResponseAddressDto(Address address){
        return address == null ? null :
                ResponseAddressDto.builder()
                        .addressLine(address.getAddressLine())
                        .city(address.getCity())
                        .country(address.getCountry())
                        .longitude(address.getLongitude())
                        .latitude(address.getLatitude())
                        .branchId(address.getBranch().getBranchId())
                        .build();
    }
}
