package org.example.centrosnetapi.services;


import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.InstrumentResponseDTO;
import org.example.centrosnetapi.repositories.InstrumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public List<InstrumentResponseDTO> findAll() {
        return instrumentRepository.findAll()
                .stream()
                .map(i -> {
                    InstrumentResponseDTO dto = new InstrumentResponseDTO();
                    dto.setId(i.getId());
                    dto.setName(i.getName());
                    return dto;
                })
                .toList();
    }
}
