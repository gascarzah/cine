package net.prueba.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.RequiredArgsConstructor;
import net.prueba.dto.request.PeliculaRequest;
import net.prueba.model.Pelicula;
import net.prueba.service.IPeliculaService;

@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@WebMvcTest(value = PeliculaController.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = { IPeliculaService.class })
@EnableSpringDataWebSupport
public class PeliculaControllerTest {
	private static final String URL = "/Peliculaes";
	@MockBean
	private IPeliculaService iPeliculaService;
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private HttpServletResponse httpServletResponse;
	
	@Mock
	private PeliculaController peliculaController;
	
	protected static ObjectMapper om = new ObjectMapper();
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(
			new PeliculaController(iPeliculaService)).setCustomArgumentResolvers(
				new PageableHandlerMethodArgumentResolver()).build();
		ObjectMapper om = new ObjectMapper();
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JacksonTester.initFields(this, om);
	}
	
	@Test
	public void getPeliculaesTestOk() throws Exception {
		List<Pelicula> PeliculaesVacios = new ArrayList<>() ;
		PeliculaesVacios.add(Pelicula.builder().idPelicula(1).nombre("pelicula1").build());
		PeliculaesVacios.add(Pelicula.builder().idPelicula(2).nombre("pelicula2").build());

		when(iPeliculaService.listar()).thenReturn(PeliculaesVacios);

		
		peliculaController.getPeliculas();
		
		mockMvc.perform(get(URL)).andDo(print()).andExpect(
			status().isOk());
	}
	
	@Test
	public void getPeliculaesTestNoOk() throws Exception {
		List<Pelicula> PeliculaesVacios = new ArrayList<>() ;

		when(iPeliculaService.listar()).thenReturn(PeliculaesVacios);

		mockMvc.perform(get(URL)).andDo(print()).andExpect(
			status().isNoContent());
	}
	

	@Test
	public void testPostWorkstationOK() throws Exception {
		
		Pelicula pelicula = Pelicula.builder().nombre("pelicula1").build();

		when(iPeliculaService.registrar(pelicula)).thenReturn(pelicula);

		
		peliculaController.registrar(PeliculaRequest.builder().nombre("HArrison").build());
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(
			om.writeValueAsString(pelicula)).characterEncoding("utf-8")).andDo(print()).andExpect(
				status().isCreated());
	}

	@Test
	public void testPutWorkstationOK() throws Exception {
		
		Pelicula pelicula = Pelicula.builder().idPelicula(1).nombre("HArrisonss").build();

		when(iPeliculaService.modificar(pelicula)).thenReturn(
				pelicula);

		peliculaController.modificar(PeliculaRequest.builder().idPelicula(1).nombre("HArrison").build());
		mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(
			om.writeValueAsString(pelicula)).characterEncoding("utf-8")).andDo(print()).andExpect(
				status().isOk());
	}

}
