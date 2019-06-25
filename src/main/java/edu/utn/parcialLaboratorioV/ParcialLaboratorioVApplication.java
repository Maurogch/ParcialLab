package edu.utn.parcialLaboratorioV;

import edu.utn.parcialLaboratorioV.model.Comentario;
import edu.utn.parcialLaboratorioV.model.Publicacion;
import edu.utn.parcialLaboratorioV.model.Usuario;
import edu.utn.parcialLaboratorioV.repository.ComentarioRepository;
import edu.utn.parcialLaboratorioV.repository.PublicacionRepository;
import edu.utn.parcialLaboratorioV.repository.UsuarioRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableAsync
public class ParcialLaboratorioVApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcialLaboratorioVApplication.class, args);
	}

	//Load data into database after running application
	//Doing this because of the UTF-8 problem when trying to post data
	@Bean
	ApplicationRunner init(ComentarioRepository comentarioRepository, PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
		return args -> {
			Usuario usuario = Usuario.builder().apellido("Perez").nombre("Juan").build();
			Usuario usuario2 = Usuario.builder().apellido("Doe").nombre("John").build();
			usuarioRepository.save(usuario);
			usuarioRepository.save(usuario2);

			Publicacion publicacion = Publicacion.builder().titulo("Publicacion1").foto("foto1").liked(false).fechaPublicacion(LocalDate.of(2019,04,12)).usuario(usuario).build();
			publicacionRepository.save(publicacion);
			Publicacion publicacion2 = Publicacion.builder().titulo("Publicacion2").foto("foto1").liked(false).fechaPublicacion(LocalDate.of(2019,04,12)).usuario(usuario).build();
			publicacionRepository.save(publicacion2);

			Comentario comentario1 = Comentario.builder().desccripcion("Descripcion, lala").fecha(LocalDateTime.now()).owner(usuario2).publicacion(publicacion).build();
			Comentario comentario2 = Comentario.builder().desccripcion("Comentario 2").fecha(LocalDateTime.now()).owner(usuario2).publicacion(publicacion).build();
			comentarioRepository.save(comentario1);
			comentarioRepository.save(comentario2);
		};
	}
}


