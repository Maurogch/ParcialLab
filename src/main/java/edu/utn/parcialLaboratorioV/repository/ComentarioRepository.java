package edu.utn.parcialLaboratorioV.repository;

import edu.utn.parcialLaboratorioV.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query(value = "DELETE FROM Comenatrio WHERE datetime = NOW() - INTERVAL ?1 SECONDS", nativeQuery = true)
    void deleteComentariosOlderThanXMinutes(Integer minutes);
}
