package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.Role;
import org.example.centrosnetapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // 🎓 Usuarios por rol
    List<User> findByRole(Role role);

    // 🎓 Usuarios por rol y centro
    List<User> findByRoleAndCenterId(Role role, Long centerId);

    List<User> findByCourseId(Long courseId);

}