package com.mabmw.autentificar.repository;

import com.mabmw.autentificar.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<UsuarioEntity, Long> {
}
