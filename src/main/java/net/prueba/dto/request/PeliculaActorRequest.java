package net.prueba.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PeliculaActorRequest {
	@NotNull(message = "Debe ingresar el id de la pelicula")
	private Integer idPelicula;
	@NotNull(message = "Debe ingresar el id del actor")
	private Integer idActor;
}
