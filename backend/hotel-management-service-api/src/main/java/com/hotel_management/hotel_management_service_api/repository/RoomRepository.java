package com.hotel_management.hotel_management_service_api.repository;

import com.hotel_management.hotel_management_service_api.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query(value = "SELECT * FROM rooms WHERE branch_id LIKE CONCAT('%', :branchId, '%')", nativeQuery = true)
    public Page<Room> findRoomByBranchId(@Param("branchId") String branchId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM rooms WHERE branch_id LIKE CONCAT('%', :branchId, '%')", nativeQuery = true)
    public long countSearchRoomByBranchId(@Param("branchId") String branchId);

}
