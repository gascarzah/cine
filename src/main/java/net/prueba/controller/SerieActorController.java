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
import net.prueba.dto.request.SerieActorRequest;
import net.prueba.exception.ResourceNotFoundException;
import net.prueba.model.Actor;
import net.prueba.model.Serie;
import net.prueba.model.SerieActor;
import net.prueba.service.ISerieActorService;
@RestController
@RequestMapping("/seriesActores")
@AllArgsConstructor
@Log4j2
public class SerieActorController {
	 private ISerieActorService iSerieActorService;

	    @PostMapping
	    public ResponseEntity<SerieActor> registrar(@Valid @RequestBody SerieActorRequest request) throws Exception {
	        
	        
	        SerieActor serieActores = SerieActor.builder()
	        		.serie(Serie.builder().idSerie(request.getIdSerie()).build())
	        		.actor(Actor.builder().idActor(request.getIdActor()).build())
	        		
	        		.build();


	        SerieActor obj = iSerieActorService.registrar(serieActores);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.CREATED);
	    }

	  

	    @GetMapping("/{id}")
	    public ResponseEntity<SerieActor> listarPorId(@PathVariable("id") Integer id) throws Exception {
	        SerieActor obj = iSerieActorService.listarPorId(id);

	        if (obj.getIdSerieActor() == null) {
	            throw new ResourceNotFoundException("Id no encontrado " + id);
	        }

	        return new ResponseEntity<SerieActor>(obj, HttpStatus.OK);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<SerieActor>> getSerieActors() throws Exception {
	        List<SerieActor> serieActores = iSerieActorService.listar();
	        if (serieActores.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<SerieActor>>(serieActores, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
	        var obj = iSerieActorService.listarPorId(id);
	        
	        if(obj == null) {
	            throw new ResourceNotFoundException("ID NO ENCONTRADO " + id);
	        }
	        iSerieActorService.eliminar(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	    
	    @PutMapping
	    public ResponseEntity<SerieActor> modificar(@Valid @RequestBody SerieActorRequest request) throws Exception {
	        SerieActor serieActor = SerieActor.builder().build();
	        BeanUtils.copyProperties(serieActor, request);
	        SerieActor obj = iSerieActorService.modificar(serieActor);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.OK);
	    }
	}
