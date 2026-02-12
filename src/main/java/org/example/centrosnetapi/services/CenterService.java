package org.example.centrosnetapi.services;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.CenterRequestDTO;
import org.example.centrosnetapi.dtos.CenterResponseDTO;
import org.example.centrosnetapi.models.Center;
import org.example.centrosnetapi.repositories.CenterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    // CREATE
    public void save(CenterRequestDTO dto) {
        Center center = new Center();
        center.setName(dto.getName());
        center.setPhone(dto.getPhone());
        center.setEmail(dto.getEmail());
        center.setWebsite(dto.getWebsite());
        center.setOpeningHours(dto.getOpeningHours());
        center.setAddress(dto.getAddress());
        center.setPostalCode(dto.getPostalCode());
        center.setTown(dto.getTown());

        centerRepository.save(center);
    }

    // READ ALL
    public List<CenterResponseDTO> findAll() {
        List<CenterResponseDTO> dtos = new ArrayList<>();

        centerRepository.findAll().forEach(c -> {
            CenterResponseDTO dto = new CenterResponseDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setPhone(c.getPhone());
            dto.setEmail(c.getEmail());
            dto.setWebsite(c.getWebsite());
            dto.setOpeningHours(c.getOpeningHours());
            dto.setAddress(c.getAddress());
            dto.setPostalCode(c.getPostalCode());
            dto.setTown(c.getTown());
            dtos.add(dto);
        });

        return dtos;
    }

    // READ BY ID
    public CenterResponseDTO findById(Long id) {
        Center c = centerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CENTER_NOT_FOUND"));

        CenterResponseDTO dto = new CenterResponseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setPhone(c.getPhone());
        dto.setEmail(c.getEmail());
        dto.setWebsite(c.getWebsite());
        dto.setOpeningHours(c.getOpeningHours());
        dto.setAddress(c.getAddress());
        dto.setPostalCode(c.getPostalCode());
        dto.setTown(c.getTown());

        return dto;
    }

    // UPDATE
    public void update(Long id, CenterRequestDTO dto) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CENTER_NOT_FOUND"));

        center.setName(dto.getName());
        center.setPhone(dto.getPhone());
        center.setEmail(dto.getEmail());
        center.setWebsite(dto.getWebsite());
        center.setOpeningHours(dto.getOpeningHours());
        center.setAddress(dto.getAddress());
        center.setPostalCode(dto.getPostalCode());
        center.setTown(dto.getTown());

        centerRepository.save(center);
    }

    // DELETE
    public void delete(Long id) {
        centerRepository.deleteById(id);
    }
}