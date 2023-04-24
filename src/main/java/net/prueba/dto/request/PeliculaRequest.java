package net.prueba.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PeliculaRequest {
    private Integer idPelicula;
    @NotNull(message = "Nombre de la pelicula es requerido")
    private String nombre;
    private String descripcion;
    

}
