package org.example.centrosnetapi.repositories;


import org.example.centrosnetapi.models.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Optional<Instrument> findByName(String name);
}
