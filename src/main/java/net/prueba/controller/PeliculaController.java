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
import net.prueba.dto.request.PeliculaRequest;
import net.prueba.exception.ResourceNotFoundException;
import net.prueba.model.Pelicula;
import net.prueba.service.IPeliculaService;
@RestController
@RequestMapping("/peliculas")
@AllArgsConstructor
@Log4j2
public class PeliculaController {
	 private IPeliculaService iPeliculaService;

	    @PostMapping
	    public ResponseEntity<Pelicula> registrar(@Valid @RequestBody PeliculaRequest request) throws Exception {
	        Pelicula pelicula = Pelicula.builder().build();
	        BeanUtils.copyProperties(pelicula, request);
	        Pelicula obj = iPeliculaService.registrar(pelicula);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.CREATED);
	    }

	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Pelicula> listarPorId(@PathVariable("id") Integer id) throws Exception {
	        Pelicula obj = iPeliculaService.listarPorId(id);

	        if (obj.getIdPelicula() == null) {
	            throw new ResourceNotFoundException("Id no encontrado " + id);
	        }

	        return new ResponseEntity<Pelicula>(obj, HttpStatus.OK);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<Pelicula>> getPeliculas() throws Exception {
	        List<Pelicula> peliculas = iPeliculaService.listar();
	        if (peliculas.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Pelicula>>(peliculas, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
	        var obj = iPeliculaService.listarPorId(id);
	        
	        if(obj == null) {
	            throw new ResourceNotFoundException("ID NO ENCONTRADO " + id);
	        }
	        iPeliculaService.eliminar(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	    
	    @PutMapping
	    public ResponseEntity<Pelicula> modificar(@Valid @RequestBody PeliculaRequest horarioRequest) throws Exception {
	        Pelicula pelicula = Pelicula.builder().build();
	        BeanUtils.copyProperties(pelicula, horarioRequest);
	        Pelicula obj = iPeliculaService.modificar(pelicula);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.OK);
	    }
	}
