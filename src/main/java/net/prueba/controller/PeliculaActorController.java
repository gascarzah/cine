package net.prueba.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.prueba.dto.request.PeliculaActorRequest;
import net.prueba.exception.ResourceNotFoundException;
import net.prueba.model.Actor;
import net.prueba.model.Pelicula;
import net.prueba.model.PeliculaActor;
import net.prueba.service.IPeliculaActorService;
@RestController
@RequestMapping("/peliculasActores")
@AllArgsConstructor
@Log4j2
public class PeliculaActorController {
	 private IPeliculaActorService iPeliculaActorService;

	    @PostMapping
	    public ResponseEntity<PeliculaActor> registrar(@Valid @RequestBody PeliculaActorRequest request) throws Exception {
	        PeliculaActor peliculaActores = PeliculaActor.builder()
	        		.pelicula(Pelicula.builder().idPelicula(request.getIdPelicula()).build())
	        		.actor(Actor.builder().idActor(request.getIdActor()).build())
	        		
	        		.build();

	        PeliculaActor obj = iPeliculaActorService.registrar(peliculaActores);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.CREATED);
	    }

	    
	    @GetMapping("/{id}")
	    public ResponseEntity<PeliculaActor> listarPorId(@PathVariable("id") Integer id) throws Exception {
	        PeliculaActor obj = iPeliculaActorService.listarPorId(id);

	        if (obj.getIdPeliculaActor() == null) {
	            throw new ResourceNotFoundException("Id no encontrado " + id);
	        }

	        return new ResponseEntity<PeliculaActor>(obj, HttpStatus.OK);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<PeliculaActor>> getPeliculaActors() throws Exception {
	        List<PeliculaActor> peliculaActores = iPeliculaActorService.listar();
	        if (peliculaActores.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<PeliculaActor>>(peliculaActores, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
	        var obj = iPeliculaActorService.listarPorId(id);
	        
	        if(obj == null) {
	            throw new ResourceNotFoundException("ID NO ENCONTRADO " + id);
	        }
	        iPeliculaActorService.eliminar(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	    
	    @PutMapping
	    public ResponseEntity<PeliculaActor> modificar(@Valid @RequestBody PeliculaActorRequest horarioRequest) throws Exception {
	        PeliculaActor peliculaActor = PeliculaActor.builder().build();
	        BeanUtils.copyProperties(peliculaActor, horarioRequest);
	        PeliculaActor obj = iPeliculaActorService.modificar(peliculaActor);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.OK);
	    }
	}
