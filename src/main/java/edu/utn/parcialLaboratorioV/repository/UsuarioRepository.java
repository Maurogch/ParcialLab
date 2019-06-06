package edu.utn.parcialLaboratorioV.repository;

import edu.utn.parcialLaboratorioV.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
