package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CenterRepository extends JpaRepository<Center, Long> {
    @Query("""
    SELECT u.center
    FROM User u
    WHERE u.email = :email
""")
    Optional<Center> findCenterByUserEmail(@Param("email") String email);
}