package edu.utn.parcialLaboratorioV.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PublicationProyectionDTO {
    @Id
    private String titulo;
    private String nombreDuenio;
    private Integer cantComentarios;
}
