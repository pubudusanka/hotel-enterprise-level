package com.hotel_management.hotel_management_service_api.repository;

import com.hotel_management.hotel_management_service_api.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query(value = "SELECT * FROM branch WHERE branch_name LIKE CONCAT('%', :searchText, '%')", nativeQuery = true)
    public Page<Branch> searchAllBranches(@Param("searchText") String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM branch WHERE branch_name LIKE CONCAT('%', :searchText, '%')", nativeQuery = true)
    public long countSearchBranches(@Param("searchText") String searchText);
}
