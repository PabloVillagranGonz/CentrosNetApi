package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByCenterId(Long centerId);

    boolean existsByNameAndCenterId(String name, Long centerId);
}