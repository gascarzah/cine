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
public class SerieActorRequest {
	@NotNull(message = "Debe ingresar el id de la serie")
	private Integer idSerie;
	@NotNull(message = "Debe ingresar el id del actor")
	private Integer idActor;
}
