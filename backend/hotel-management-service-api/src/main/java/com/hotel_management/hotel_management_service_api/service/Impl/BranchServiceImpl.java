package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestBranchDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseBranchDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseBranchPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.Branch;
import com.hotel_management.hotel_management_service_api.entity.Hotel;
import com.hotel_management.hotel_management_service_api.repository.BranchRepository;
import com.hotel_management.hotel_management_service_api.repository.HotelRepository;
import com.hotel_management.hotel_management_service_api.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final HotelRepository hotelRepository;

    @Override
    public void createBranch(RequestBranchDto data) throws SQLException {
        branchRepository.save(toBranch(data));
    }

    @Override
    public void updateBranch(RequestBranchDto data, String branchId) {

    }

    @Override
    public void deleteBranch(String branchId) {

    }

    @Override
    public ResponseBranchDto findByBranchId(String branchId) throws SQLException {
        return null;
    }

    @Override
    public ResponseBranchPaginationDto findBranches(int page, int size, String searchText) {
        return null;
    }

    @Override
    public ResponseBranchPaginationDto findBranchesByHotelId(int page, int size, String hotelId) {
        return null;
    }

    //dto to entity
    private Branch toBranch(RequestBranchDto data) throws SQLException {
        return data == null ? null :
                Branch.builder()
                        .branchName(data.getBranchName())
                        .branchType(data.getBranchType())
                        .roomCount(data.getRoomCount())
                        .hotel(findHotelById(data.getHotelId()))
                        .build();
    }

    //find hotel related to the branch
    private Hotel findHotelById(String hotelId) throws SQLException {
        return hotelRepository.findById(hotelId).orElse(null);
    }
}
