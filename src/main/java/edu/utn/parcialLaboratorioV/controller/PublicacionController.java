package edu.utn.parcialLaboratorioV.controller;

import edu.utn.parcialLaboratorioV.model.Publicacion;
import edu.utn.parcialLaboratorioV.model.Usuario;
import edu.utn.parcialLaboratorioV.repository.ComentarioRepository;
import edu.utn.parcialLaboratorioV.repository.PublicacionRepository;
import edu.utn.parcialLaboratorioV.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid Publicacion publicacion){
        try {
            if(isNull(publicacion.getUsuario().getId()))
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Publicación sin usuario");
            if(!usuarioRepository.findById(publicacion.getUsuario().getId()).isPresent())
                usuarioRepository.save(publicacion.getUsuario());

            publicacionRepository.save(publicacion);
        } catch (DataIntegrityViolationException e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with data integrity");
        }
    }

    @GetMapping("{id}/publicaciones")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Publicacion>> getAllPublicationsByUserId(@PathVariable("id") Integer id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Usuario no encontrado con el id: %s", id)));

        if(!usuario.getPublicaciones().isEmpty())
            return ResponseEntity.ok(usuario.getPublicaciones());
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuario.getPublicaciones());
    }
}
