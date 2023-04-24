package net.prueba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table

public class SerieActor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSerieActor;

	@ManyToOne
	@JoinColumn(name = "id_serie", nullable = false)
	private Serie serie;
	
	@ManyToOne
	@JoinColumn(name = "id_actor", nullable = false)
	private Actor actor;
	
}
