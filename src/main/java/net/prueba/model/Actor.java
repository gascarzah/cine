package net.prueba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idActor;
    
    private String nombres;
    
    
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "actor", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<PeliculaActor> peliculasActores  = new ArrayList<>();
    
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "actor", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<SerieActor> seriesActores  = new ArrayList<>();
}
