package edu.utn.parcialLaboratorioV.controller;

import edu.utn.parcialLaboratorioV.model.Comentario;
import edu.utn.parcialLaboratorioV.model.Usuario;
import edu.utn.parcialLaboratorioV.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid Usuario usuario, @RequestHeader("User-Agent") String userAgent){
        try {
            usuario.setBrowser(userAgent);
            usuarioRepository.save(usuario);
            //Validations done with javax validation
        } catch (DataIntegrityViolationException e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with data integrity");
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        if(!usuarios.isEmpty())
            return ResponseEntity.ok(usuarios);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarios);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario getById(@PathVariable("id") Integer id){
        //Returning bad request if pathvariable is not an integer

        return usuarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Usuario no encontrado con el id: %s", id)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, Usuario usuario){
        Usuario usuario2 = usuarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Usuario no encontrado con el id: %s", id)));

        usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Usuario no encontrado con el id: %s", id)));

        usuarioRepository.delete(usuario);
    }
}
