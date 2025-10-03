package com.hotel_management.hotel_management_service_api.repository;

import com.hotel_management.hotel_management_service_api.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, String> {

    @Query(value = "SELECT * FROM address WHERE branch_id LIKE CONCAT('%', :branchId, '%')", nativeQuery = true)
    public Address findAddressByBranchId(@Param("branchId") String branchId);

    @Query(value = "SELECT * FROM address WHERE city LIKE CONCAT('%', :city, '%')", nativeQuery = true)
    public Page<Address> searchAllAddresses(@Param("searchText") String city, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM address WHERE city LIKE CONCAT('%', :city, '%')", nativeQuery = true)
    public long countSearchAddresses(@Param("searchText") String city);
}
