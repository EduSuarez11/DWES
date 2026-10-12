package es.daw.simulacro.mvc.mvc_hoteles.repository;

import es.daw.simulacro.mvc.mvc_hoteles.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String email);
}
