package com.hotel_management.hotel_management_service_api.repository;

import com.hotel_management.hotel_management_service_api.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    @Query(value = "SELECT * FROM hotel WHERE hotel_name LIKE CONCAT('%', :searchText, '%') AND active_status=true", nativeQuery = true)
    public Page<Hotel> searchAllHotels(@Param("searchText") String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM hotel WHERE hotel_name LIKE CONCAT('%', :searchText, '%') AND active_status=true", nativeQuery = true)
    public long countSearchHotels(@Param("searchText") String searchText);
}
