package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestRoomDto;
import com.hotel_management.hotel_management_service_api.service.RoomService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/hotel-service/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/user/create")
    public ResponseEntity<StandardResponseDto> createRoom(@RequestBody RequestRoomDto roomDto) {
        roomService.createRoom(roomDto);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Room Creation Successfully!", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/admin/update/{id}")
    public ResponseEntity<StandardResponseDto> updateRoom(
            @PathVariable("id") String roomId,
            @RequestBody RequestRoomDto data) {
        roomService.updateRoom(data, roomId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Room updated successfully", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/host/delete/{id}")
    public ResponseEntity<StandardResponseDto> deleteRoom(@PathVariable("id") String roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204, "Room Deleted Successfully!", null
                ),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/visitor/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findRoomById(@PathVariable("id") String roomId) throws SQLException {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Room Found!", roomService.findRoomById(roomId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-by-branch-id")
    public ResponseEntity<StandardResponseDto> findRoomsByBranchId(
            @RequestParam String branchId,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Rooms!", roomService.findRoomsInBranchId(page,size,branchId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-by-branch-name")
    public ResponseEntity<StandardResponseDto> findRoomsByBranchName(
            @RequestParam String branchName,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Rooms!", roomService.findRoomsInBranchName(page,size,branchName)
                ),
                HttpStatus.OK
        );
    }
}
