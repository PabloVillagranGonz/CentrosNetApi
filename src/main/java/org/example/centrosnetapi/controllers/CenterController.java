package org.example.centrosnetapi.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.CenterRequestDTO;
import org.example.centrosnetapi.dtos.CenterResponseDTO;
import org.example.centrosnetapi.services.CenterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Centers", description = "Gestión de centros")
public class CenterController {

    private final CenterService centerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CenterRequestDTO dto) {
        try {
            centerService.save(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Error al crear centro",
                    e
            );
        }
    }

    @GetMapping("/")
    public List<CenterResponseDTO> getAll() {
        return centerService.findAll();
    }

    @GetMapping("/{id}")
    public CenterResponseDTO getById(@PathVariable Long id) {
        try {
            return centerService.findById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Centro no encontrado",
                    e
            );
        }
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody CenterRequestDTO dto
    ) {
        try {
            centerService.update(id, dto);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Error al actualizar centro",
                    e
            );
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        centerService.delete(id);
    }
}