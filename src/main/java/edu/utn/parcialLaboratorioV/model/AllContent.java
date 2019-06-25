package edu.utn.parcialLaboratorioV.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AllContent {
    List<Usuario> usuarios;
    List<Publicacion> publicaciones;
    List<Comentario> comentarios;
}
