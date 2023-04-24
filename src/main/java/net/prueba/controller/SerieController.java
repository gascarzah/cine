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
import net.prueba.dto.request.SerieRequest;
import net.prueba.exception.ResourceNotFoundException;
import net.prueba.model.Serie;
import net.prueba.service.ISerieService;
@RestController
@RequestMapping("/series")
@AllArgsConstructor
@Log4j2
public class SerieController {
	 private ISerieService iSerieService;

	    @PostMapping
	    public ResponseEntity<Serie> registrar(@Valid @RequestBody SerieRequest request) throws Exception {
	        Serie serie = Serie.builder().build();
	        BeanUtils.copyProperties(serie, request);
	        Serie obj = iSerieService.registrar(serie);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.CREATED);
	    }

	  

	    @GetMapping("/{id}")
	    public ResponseEntity<Serie> listarPorId(@PathVariable("id") Integer id) throws Exception {
	        Serie obj = iSerieService.listarPorId(id);

	        if (obj.getIdSerie() == null) {
	            throw new ResourceNotFoundException("Id no encontrado " + id);
	        }

	        return new ResponseEntity<Serie>(obj, HttpStatus.OK);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<Serie>> getSeries() throws Exception {
	        List<Serie> series = iSerieService.listar();
	        if (series.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Serie>>(series, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
	        var obj = iSerieService.listarPorId(id);
	        
	        if(obj == null) {
	            throw new ResourceNotFoundException("ID NO ENCONTRADO " + id);
	        }
	        iSerieService.eliminar(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	    
	    @PutMapping
	    public ResponseEntity<Serie> modificar(@Valid @RequestBody SerieRequest request) throws Exception {
	        Serie serie = Serie.builder().build();
	        BeanUtils.copyProperties(serie, request);
	        Serie obj = iSerieService.modificar(serie);

	        log.info("objeto creado " + obj);
	        return new ResponseEntity<>(obj, HttpStatus.OK);
	    }
	}
