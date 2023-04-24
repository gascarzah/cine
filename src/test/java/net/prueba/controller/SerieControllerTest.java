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
import net.prueba.dto.request.SerieRequest;
import net.prueba.model.Serie;
import net.prueba.service.ISerieService;

@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@WebMvcTest(value = SerieController.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = { ISerieService.class })
@EnableSpringDataWebSupport
public class SerieControllerTest {
	private static final String URL = "/Seriees";
	@MockBean
	private ISerieService iSerieService;
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private HttpServletResponse httpServletResponse;
	
	@Mock
	private SerieController serieController;
	
	protected static ObjectMapper om = new ObjectMapper();
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(
			new SerieController(iSerieService)).setCustomArgumentResolvers(
				new PageableHandlerMethodArgumentResolver()).build();
		ObjectMapper om = new ObjectMapper();
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JacksonTester.initFields(this, om);
	}
	
	@Test
	public void getSerieesTestOk() throws Exception {
		List<Serie> SerieesVacios = new ArrayList<>() ;
		SerieesVacios.add(Serie.builder().idSerie(1).nombre("seri1").build());
		SerieesVacios.add(Serie.builder().idSerie(2).nombre("serie2").build());

		when(iSerieService.listar()).thenReturn(SerieesVacios);

		
		serieController.getSeries();
		
		mockMvc.perform(get(URL)).andDo(print()).andExpect(
			status().isOk());
	}
	
	@Test
	public void getSerieesTestNoOk() throws Exception {
		List<Serie> SerieesVacios = new ArrayList<>() ;

		when(iSerieService.listar()).thenReturn(SerieesVacios);

		mockMvc.perform(get(URL)).andDo(print()).andExpect(
			status().isNoContent());
	}
	

	@Test
	public void testPostWorkstationOK() throws Exception {
		
		Serie serie = Serie.builder().nombre("Serie1").build();

		when(iSerieService.registrar(serie)).thenReturn(serie);

		
		serieController.registrar(SerieRequest.builder().nombre("Serie1").build());
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(
			om.writeValueAsString(serie)).characterEncoding("utf-8")).andDo(print()).andExpect(
				status().isCreated());
	}

	@Test
	public void testPutWorkstationOK() throws Exception {
		
		Serie serie = Serie.builder().idSerie(1).nombre("Serie1").build();

		when(iSerieService.modificar(serie)).thenReturn(
				serie);

		serieController.modificar(SerieRequest.builder().idSerie(1).nombre("Serie1").build());
		mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(
			om.writeValueAsString(serie)).characterEncoding("utf-8")).andDo(print()).andExpect(
				status().isOk());
	}

}
