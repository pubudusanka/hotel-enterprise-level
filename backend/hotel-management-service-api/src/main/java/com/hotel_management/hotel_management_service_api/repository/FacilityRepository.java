package com.hotel_management.hotel_management_service_api.repository;

import com.hotel_management.hotel_management_service_api.entity.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacilityRepository extends JpaRepository<Facility, String> {

    @Query(value = "SELECT * FROM facility WHERE facility_id LIKE CONCAT('%', :facilityId, '%')", nativeQuery = true)
    public Page<Facility> searchAllRoomsByFacility(@Param("facilityId") String facilityId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM facility WHERE facility_id LIKE CONCAT('%', :facilityId, '%')", nativeQuery = true)
    public long countSearchRoomsByFacility(@Param("facilityId") String facilityId);

    @Query(value = "SELECT * FROM facility WHERE facility_name LIKE CONCAT('%', :facilityName, '%')", nativeQuery = true)
    public Facility findFacilitiesByFacilityName(String facilityName);

    @Query(value = "SELECT * FROM facility WHERE facility_id LIKE CONCAT('%', :searchText, '%')", nativeQuery = true)
    public Page<Facility> searchAllFacilities(@Param("searchText") String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM facility WHERE facility_id LIKE CONCAT('%', :searchText, '%')", nativeQuery = true)
    public long countSearchFacilities(@Param("searchText") String searchText);
}
