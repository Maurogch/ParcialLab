package edu.utn.parcialLaboratorioV.repository;

import edu.utn.parcialLaboratorioV.model.PublicationProyectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationProyectionDTORepository extends JpaRepository<PublicationProyectionDTO, String> {
    @Query(value = "select p.titulo, u.nombre as nombre_duenio, ifnull(count(c.id),0) as cant_comentarios " +
            "from publicacion p " +
            "inner join usuario u " +
            "on p.id_usuario = u.id " +
            "left join comentario c " +
            "on c.id_publicacion = p.id " +
            "group by p.id", nativeQuery = true)
    List<PublicationProyectionDTO> getCantComentarios();
}
