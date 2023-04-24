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
import net.prueba.dto.request.ActorRequest;
import net.prueba.model.Actor;
import net.prueba.service.IActorService;

@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@WebMvcTest(value = ActorController.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = { IActorService.class })
@EnableSpringDataWebSupport
public class ActorControllerTest {
	private static final String URL = "/actores";
	@MockBean
	private IActorService iActorService;
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private HttpServletResponse httpServletResponse;
	
	@Mock
	private ActorController actorController;
	
	protected static ObjectMapper om = new ObjectMapper();
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(
			new ActorController(iActorService)).setCustomArgumentResolvers(
				new PageableHandlerMethodArgumentResolver()).build();
		ObjectMapper om = new ObjectMapper();
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JacksonTester.initFields(this, om);
	}
	
	@Test
	public void getActoresTestOk() throws Exception {
		List<Actor> actoresVacios = new ArrayList<>() ;
		actoresVacios.add(Actor.builder().idActor(1).nombres("HArrison").build());
		actoresVacios.add(Actor.builder().idActor(2).nombres("La roca").build());

		when(iActorService.listar()).thenReturn(actoresVacios);

		
		actorController.getActors();
		
		mockMvc.perform(get(URL)).andDo(print()).andExpect(
			status().isOk());
	}
	
	@Test
	public void getActoresTestNoOk() throws Exception {
		List<Actor> actoresVacios = new ArrayList<>() ;

		when(iActorService.listar()).thenReturn(actoresVacios);

		mockMvc.perform(get(URL)).andDo(print()).andExpect(
			status().isNoContent());
	}
	

	@Test
	public void testPostWorkstationOK() throws Exception {
		
		Actor actor = Actor.builder().nombres("HArrison").build();

		when(iActorService.registrar(actor)).thenReturn(actor);

		
		actorController.registrar(ActorRequest.builder().nombres("HArrison").build());
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(
			om.writeValueAsString(actor)).characterEncoding("utf-8")).andDo(print()).andExpect(
				status().isCreated());
	}

	@Test
	public void testPutWorkstationOK() throws Exception {
		
		Actor actor = Actor.builder().idActor(1).nombres("HArrisonss").build();

		when(iActorService.modificar(actor)).thenReturn(
				actor);

		actorController.modificar(ActorRequest.builder().idActor(1).nombres("HArrison").build());
		mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(
			om.writeValueAsString(actor)).characterEncoding("utf-8")).andDo(print()).andExpect(
				status().isOk());
	}

}
