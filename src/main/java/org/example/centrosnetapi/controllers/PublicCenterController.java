package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.models.Center;
import org.example.centrosnetapi.repositories.CenterRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public/centers")
@RequiredArgsConstructor
@CrossOrigin
public class PublicCenterController {

    private final CenterRepository centerRepository;

    @GetMapping("/{id}")
    public Map<String, Object> getCenter(@PathVariable Long id) {
        Center c = centerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CENTER_NOT_FOUND"));

        return Map.of(
                "id", c.getId(),
                "name", c.getName(),
                "phone", c.getPhone(),
                "email", c.getEmail(),
                "website", c.getWebsite(),
                "address", c.getAddress(),
                "town", c.getTown(),
                "openingHours", c.getOpeningHours(),
                "postalCode", c.getPostalCode()
        );
    }

    @GetMapping("/me")
    public Map<String, Object> getMyCenter(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal
    ) {
        String email = principal.getUsername();

        Center c = centerRepository
                .findCenterByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("CENTER_NOT_ASSIGNED"));

        return Map.of(
                "id", c.getId(),
                "name", c.getName(),
                "phone", c.getPhone(),
                "email", c.getEmail(),
                "website", c.getWebsite(),
                "address", c.getAddress(),
                "town", c.getTown(),
                "openingHours", c.getOpeningHours(),
                "postalCode", c.getPostalCode()
        );
    }
}
