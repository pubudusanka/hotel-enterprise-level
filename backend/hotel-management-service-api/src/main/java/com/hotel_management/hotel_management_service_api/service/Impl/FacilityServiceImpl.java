package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestFacilityDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseFacilityDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseBranchPaginationDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseFacilityPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.Facility;
import com.hotel_management.hotel_management_service_api.entity.Room;
import com.hotel_management.hotel_management_service_api.repository.FacilityRepository;
import com.hotel_management.hotel_management_service_api.repository.RoomRepository;
import com.hotel_management.hotel_management_service_api.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final RoomRepository roomRepository;
    private final FacilityRepository facilityRepository;

    @Override
    public void createFacility(RequestFacilityDto data) {
        facilityRepository.save(toFacility(data));
    }

    @Override
    public void updateFacility(RequestFacilityDto data, String facilityId) {
        Facility selectedFacility = facilityRepository.findById(facilityId).orElseThrow(
                () -> new RuntimeException("Facility not found with id: " + facilityId)
        );
        selectedFacility.setFacilityName(data.getFacilityName());
        selectedFacility.setRoom(findRoomByRoomId(data.getRoomId()));
        facilityRepository.save(selectedFacility);
    }

    @Override
    public void deleteFacility(String facilityId) {
        Facility selectedFacility = facilityRepository.findById(facilityId).orElseThrow(
                () -> new RuntimeException("Facility not found with id: " + facilityId)
        );
        facilityRepository.delete(selectedFacility);
    }

    @Override
    public ResponseFacilityDto findFacilityByFacilityId(String facilityId) {
        Facility selectedFacility = facilityRepository.findById(facilityId).orElseThrow(
                () -> new RuntimeException("Facility not found with id: " + facilityId)
        );
        return toResponseFacilityDto(selectedFacility);
    }

    @Override
    public ResponseFacilityPaginationDto findRoomsByFacilityId(int page, int size, String facilityId) {
        return ResponseFacilityPaginationDto.builder()
                .dataCount(facilityRepository.countSearchRoomsByFacility(facilityId))
                .dataList(
                        facilityRepository.searchAllRoomsByFacility(facilityId, PageRequest.of(page, size))
                                .stream().map(this::toResponseFacilityDto).toList()
                )
                .build();
    }

    @Override
    public ResponseFacilityPaginationDto findRoomsByFacilityName(int page, int size, String facilityName) {
        Facility facilitiesByFacilityName = facilityRepository.findFacilitiesByFacilityName(facilityName);
        String facilityId = facilitiesByFacilityName.getFacilityId();

        return ResponseFacilityPaginationDto.builder()
                .dataCount(facilityRepository.countSearchRoomsByFacility(facilityId))
                .dataList(
                        facilityRepository.searchAllRoomsByFacility(facilityId, PageRequest.of(page, size))
                                .stream().map(this::toResponseFacilityDto).toList()
                )
                .build();
    }

    @Override
    public ResponseFacilityPaginationDto findAllFacilities(int page, int size, String searchText) {
        return ResponseFacilityPaginationDto.builder()
                .dataCount(facilityRepository.countSearchFacilities(searchText))
                .dataList(
                        facilityRepository.searchAllFacilities(searchText, PageRequest.of(page, size))
                        .stream().map(this::toResponseFacilityDto).toList()
                )
                .build();
    }

    // dto mapped to the entity
    private Facility toFacility(RequestFacilityDto data){
        return data == null ? null :
                Facility.builder()
                        .facilityName(data.getFacilityName())
                        .room(findRoomByRoomId(data.getRoomId()))
                        .build();
    }

    // find room related to the facility
    private Room findRoomByRoomId(String roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    // response facility (entity to dto)
    private ResponseFacilityDto toResponseFacilityDto(Facility facility){
        return facility == null ? null :
                ResponseFacilityDto.builder()
                        .facilityId(facility.getFacilityId())
                        .facilityName(facility.getFacilityName())
                        .roomId(facility.getRoom().getRoomId())
                        .build();
    }
}
