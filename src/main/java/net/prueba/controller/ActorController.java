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

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.prueba.dto.request.ActorRequest;
import net.prueba.exception.ResourceNotFoundException;
import net.prueba.model.Actor;
import net.prueba.service.IActorService;

@RestController
@RequestMapping("/actores")
@AllArgsConstructor
@Log4j2
public class ActorController {

	private IActorService iActorService;

	@ApiOperation(value = "REgistrar actor", notes = "Se registra un actor.")
	@PostMapping
	public ResponseEntity<Actor> registrar(@Valid @RequestBody ActorRequest request) throws Exception {
		log.info("objeto request llega " + request);
		Actor actor = Actor.builder().build();
		BeanUtils.copyProperties(actor, request);
		log.info("objeto creado actor " + actor.getNombres());
		Actor obj = iActorService.registrar(actor);
//
		log.info("objeto creado " + obj);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Actor> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Actor obj = iActorService.listarPorId(id);

		if (obj.getIdActor() == null) {
			throw new ResourceNotFoundException("Id no encontrado " + id);
		}

		return new ResponseEntity<Actor>(obj, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Actor>> getActors() throws Exception {
		List<Actor> actores = iActorService.listar();
		if (actores.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Actor>>(actores, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		var obj = iActorService.listarPorId(id);

		if (obj == null) {
			throw new ResourceNotFoundException("ID NO ENCONTRADO " + id);
		}
		iActorService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Actor> modificar(@Valid @RequestBody ActorRequest request) throws Exception {
		Actor actor = Actor.builder().build();
		BeanUtils.copyProperties(actor, request);
		Actor obj = iActorService.modificar(actor);

		log.info("objeto creado " + obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
}
