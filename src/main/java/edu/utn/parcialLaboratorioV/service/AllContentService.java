package edu.utn.parcialLaboratorioV.service;

import edu.utn.parcialLaboratorioV.model.AllContent;
import edu.utn.parcialLaboratorioV.model.Comentario;
import edu.utn.parcialLaboratorioV.model.Publicacion;
import edu.utn.parcialLaboratorioV.model.Usuario;
import edu.utn.parcialLaboratorioV.repository.ComentarioRepository;
import edu.utn.parcialLaboratorioV.repository.PublicacionRepository;
import edu.utn.parcialLaboratorioV.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AllContentService {
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Async("Executor")
    public CompletableFuture<List<Comentario>> getComentarios(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(comentarioRepository.findAll());
    }

    @Async("Executor")
    public CompletableFuture<List<Publicacion>> getPublicaciones(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(publicacionRepository.findAll());
    }

    @Async("Executor")
    public CompletableFuture<List<Usuario>> getUsuarios(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(usuarioRepository.findAll());
    }
}
