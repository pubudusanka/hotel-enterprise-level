package com.hotel_management.hotel_management_service_api.service;

import com.hotel_management.hotel_management_service_api.dto.request.RequestBranchDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseBranchDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseBranchPaginationDto;
import java.sql.SQLException;

public interface BranchService {
    public void createBranch(RequestBranchDto data) throws SQLException;
    public void updateBranch(RequestBranchDto data, String branchId) throws SQLException;
    public void deleteBranch(String branchId);
    public ResponseBranchDto findByBranchId(String branchId) throws SQLException;
    public ResponseBranchPaginationDto findBranches(int page, int size, String searchText);
    public ResponseBranchPaginationDto findBranchesByHotelId(int page, int size, String hotelId);
}
