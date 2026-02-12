package org.example.centrosnetapi.services;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.RoomRequestDTO;
import org.example.centrosnetapi.models.Center;
import org.example.centrosnetapi.models.Room;
import org.example.centrosnetapi.repositories.CenterRepository;
import org.example.centrosnetapi.repositories.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final CenterRepository centerRepository;

    // ================= CREATE =================
    @PreAuthorize("hasRole('ADMIN')")
    public Room create(RoomRequestDTO dto) {

        if (roomRepository.existsByNameAndCenterId(
                dto.getName(), dto.getCenterId()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "ROOM_ALREADY_EXISTS"
            );
        }

        Center center = centerRepository.findById(dto.getCenterId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "CENTER_NOT_FOUND"
                        )
                );

        Room room = new Room();
        room.setName(dto.getName());
        room.setNotes(dto.getNotes());
        room.setCenter(center);

        return roomRepository.save(room);
    }

    // ================= READ =================
    @PreAuthorize("isAuthenticated()")
    public List<Room> findByCenter(Long centerId) {
        return roomRepository.findByCenterId(centerId);
    }

    // ================= DELETE =================
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "ROOM_NOT_FOUND"
            );
        }
        roomRepository.deleteById(id);
    }
}