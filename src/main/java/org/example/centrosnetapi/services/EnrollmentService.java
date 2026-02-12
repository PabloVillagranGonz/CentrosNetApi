package org.example.centrosnetapi.services;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.EnrollmentRequestDTO;
import org.example.centrosnetapi.dtos.EnrollmentResponseDTO;
import org.example.centrosnetapi.models.*;
import org.example.centrosnetapi.repositories.*;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO dto) {

        Enrollment e = new Enrollment();
        e.setStudent(
                userRepository.findById(dto.getStudentId())
                        .orElseThrow(() -> new RuntimeException("STUDENT_NOT_FOUND"))
        );
        e.setSubject(
                subjectRepository.findById(dto.getSubjectId())
                        .orElseThrow(() -> new RuntimeException("SUBJECT_NOT_FOUND"))
        );
        e.setCourse(
                courseRepository.findById(dto.getCourseId())
                        .orElseThrow(() -> new RuntimeException("COURSE_NOT_FOUND"))
        );
        e.setStatus("active");

        Enrollment saved = enrollmentRepository.save(e);

        return EnrollmentResponseDTO.builder()
                .id(saved.getId())

                .studentId(saved.getStudent().getId())
                .studentName(
                        saved.getStudent().getNombre() + " " + saved.getStudent().getApellidos()
                )

                .subjectId(saved.getSubject().getId())
                .subjectName(saved.getSubject().getName())

                .courseId(saved.getCourse().getId())
                .courseName(saved.getCourse().getName())

                .build();
    }

    public List<EnrollmentResponseDTO> getByStudent(Long studentId) {
        return enrollmentRepository.findByStudent_Id(studentId)
                .stream()
                .map(e -> EnrollmentResponseDTO.builder()
                        .id(e.getId())

                        .studentId(e.getStudent().getId())
                        .studentName(
                                e.getStudent().getNombre() + " " + e.getStudent().getApellidos()
                        )

                        .subjectId(e.getSubject().getId())
                        .subjectName(e.getSubject().getName())

                        .courseId(e.getCourse().getId())
                        .courseName(e.getCourse().getName())

                        .build()
                )
                .toList();
    }
}