package edu.utn.parcialLaboratorioV.controller;

import edu.utn.parcialLaboratorioV.model.Comentario;
import edu.utn.parcialLaboratorioV.model.Usuario;
import edu.utn.parcialLaboratorioV.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;
    /*@Value("${comentario.schedule}")
    private Integer time;*/

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid Comentario comentario){
        try {
            if(isNull(comentario.getOwner().getId()))
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Comenatrio sin usuario seteado");
            if(isNull(comentario.getPublicacion().getId()))
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Comenatrio sin publicacion seteada");

            comentarioRepository.save(comentario);
        } catch (DataIntegrityViolationException e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with data integrity");
        }


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        Comentario comentario = comentarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Comentario no encontrado con el id: %s", id)));

        comentarioRepository.delete(comentario);
    }

    /*@Scheduled(cron = "${comentario.schedule}") //cron, every five minutes everyday
    public void deleteVotes(){
        comentarioRepository.deleteComentariosOlderThanXMinutes(time);
    }*/
}
