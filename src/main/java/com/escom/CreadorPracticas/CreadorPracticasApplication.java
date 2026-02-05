package com.escom.CreadorPracticas;

import com.escom.CreadorPracticas.Entity.Usuario;
import com.escom.CreadorPracticas.Repository.UsuarioRepository;
import com.escom.CreadorPracticas.Util.Rol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
//@EntityScan("com.escom.CreadorProyectos")
public class CreadorPracticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreadorPracticasApplication.class, args);
	}

	// La siguiente funcion es para poblar la base con lo minimo indispensable
	// cuando la base  de datos de todo el proyecto esta vacia.
	// Tambien se puede iptar por un .sql con los inserts a la base de datos.
/*
	@Bean
	CommandLineRunner init(UsuarioRepository userRepository){
		return args -> {

			if (userRepository.findByUsername("master").isPresent()) {
				return; // ya existe, no crea otro
			}

			Usuario masterUser = Usuario.builder()
					.username("master")
					.email("bjgnbfbdhj@gmail.com")
					.nombre("master")
					.apellidoPaterno("master")
					.apellidoMaterno("master")
					.fechaNacimiento(LocalDate.now())
					.passwordHash(new BCryptPasswordEncoder().encode("secret"))
					.enabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.rol(Rol.ADMIN)
					.build();

			userRepository.save(masterUser);
		};
	}
*/

}
