package org.example.centrosnetapi.services;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.UserResponseDTO;
import org.example.centrosnetapi.models.Center;
import org.example.centrosnetapi.models.Role;
import org.example.centrosnetapi.models.User;
import org.example.centrosnetapi.repositories.CenterRepository;
import org.example.centrosnetapi.repositories.ClassSessionRepository;
import org.example.centrosnetapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final CenterRepository centerRepository;
    private final ClassSessionRepository classSessionRepository;

    // =============================
    // READ ALL TEACHERS
    // =============================
    public List<User> findAllTeachers() {
        return userRepository.findByRole(Role.TEACHER);
    }

    // =============================
    // READ TEACHERS BY CENTER
    // =============================
    public List<User> findTeachersByCenter(Long centerId) {
        return userRepository.findByRoleAndCenterId(Role.TEACHER, centerId);
    }

    // =============================
    // ASSIGN TEACHER TO CENTER
    // =============================
    public User assignToCenter(Long teacherId, Long centerId) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "TEACHER_NOT_FOUND"
                        )
                );

        if (teacher.getRole() != Role.TEACHER) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "USER_IS_NOT_TEACHER"
            );
        }

        Center center = centerRepository.findById(centerId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "CENTER_NOT_FOUND"
                        )
                );

        teacher.setCenter(center);
        return userRepository.save(teacher);
    }

    // =============================
    // REMOVE TEACHER FROM CENTER
    // =============================
    public void removeFromCenter(Long teacherId) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "TEACHER_NOT_FOUND"
                        )
                );

        if (teacher.getRole() != Role.TEACHER) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "USER_IS_NOT_TEACHER"
            );
        }

        teacher.setCenter(null);
        userRepository.save(teacher);
    }

    public List<UserResponseDTO> getStudentsForTeacher(Long teacherId) {
        return classSessionRepository.findStudentsForTeacher(teacherId)
                .stream()
                .map(this::toUserDTO)
                .toList();
    }

    private UserResponseDTO toUserDTO(User u) {
        return UserResponseDTO.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .role(u.getRole())
                .centerId(
                        u.getCenter() != null ? u.getCenter().getId() : null
                )
                .instrumentId(
                        u.getInstrument() != null ? u.getInstrument().getId() : null
                )
                .build();
    }
}