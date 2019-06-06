package edu.utn.parcialLaboratorioV.repository;

import edu.utn.parcialLaboratorioV.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
}
