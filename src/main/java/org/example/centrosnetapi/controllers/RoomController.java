package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.RoomRequestDTO;
import org.example.centrosnetapi.dtos.RoomResponseDTO;
import org.example.centrosnetapi.models.Room;
import org.example.centrosnetapi.services.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {

    private final RoomService roomService;

    // CREATE
    @PostMapping
    public RoomResponseDTO create(@RequestBody RoomRequestDTO dto) {
        Room room = roomService.create(dto);
        return toDTO(room);
    }

    // READ BY CENTER
    @GetMapping("/center/{centerId}")
    public List<RoomResponseDTO> getByCenter(@PathVariable Long centerId) {
        return roomService.findByCenter(centerId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }

    private RoomResponseDTO toDTO(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getNotes(),
                room.getCenter().getId()
        );
    }
}
