package io.comunik;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.TipoComunicacao;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ComunikApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
    @Test
    public void POST_envioComunicacao_AdicionaNovo_retornaIdParaConsulta() throws JsonProcessingException, Exception {
    	var comunicacaoEntity = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
    	
    	mockMvc.perform(post("/enviocomunicacao")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(comunicacaoEntity))
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$.id").exists());
    }
    
    @Test
    public void POST_envioComunicacao_semDataHora_retornaErro() throws JsonProcessingException, Exception {
    	var comunicacaoEntity = EnvioComunicacao.builder()
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
    	
    	mockMvc.perform(post("/enviocomunicacao")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(comunicacaoEntity))
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.message").exists())
        		.andExpect(jsonPath("$.message").value(equalTo("Data envio não pode ser vazio, ")));
    }
    
    @Test
    public void POST_envioComunicacao_semDestinatario_retornaErro() throws JsonProcessingException, Exception {
    	var comunicacaoEntity = EnvioComunicacao.builder()
    			.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
    	
    	mockMvc.perform(post("/enviocomunicacao")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(comunicacaoEntity))
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.message").exists())
        		.andExpect(jsonPath("$.message").value(equalTo("Destinatario não pode ser vazio, ")));
    }
    
    @Test
    public void POST_envioComunicacao_semMensagem_retornaErro() throws JsonProcessingException, Exception {
    	var comunicacaoEntity = EnvioComunicacao.builder()
    			.destinatario("alexandreaw@gmail.com")
    			.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
    	
    	mockMvc.perform(post("/enviocomunicacao")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(comunicacaoEntity))
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.message").exists())
        		.andExpect(jsonPath("$.message").value(equalTo("Mensagem não pode ser vazio, ")));
    }
    
    @Test
    public void POST_envioComunicacao_comDataHoraInferiorAtual_retornaErro() throws JsonProcessingException, Exception {
    	var comunicacaoEntity = EnvioComunicacao.builder()
    			.dataHoraEnvio(LocalDateTime.of(2020, Month.FEBRUARY, 21, 5, 44))
    			.destinatario("alexandreaw@gmail.com")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.mensagem("Mensagem")
				.build();
    	
    	mockMvc.perform(post("/enviocomunicacao")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(comunicacaoEntity))
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.message").exists())
        		.andExpect(jsonPath("$.message").value(equalTo("DataHora 2020-02-21T05:44 invalida ou menor que datahora atual")));
    }
    
    @Test
    @Sql({"/get_data.sql"})
    public void GET_consultaStatus_informaId_retornaStatusAgendamento() throws JsonProcessingException, Exception {
    	mockMvc.perform(get("/enviocomunicacao/status/c68f204c-7d9b-47de-b2ad-c19da3db2a92")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.id").exists())
        		.andExpect(jsonPath("$.status").exists())
        		.andExpect(jsonPath("$.status").value(equalTo("ENVIADO")));
    }
    
    @Test
    public void GET_consultaStatus_informaIdInexistente_retornaMensagemErro() throws JsonProcessingException, Exception {
    	mockMvc.perform(get("/enviocomunicacao/status/418a2796-16c0-4d62-b682-10624df3f3c5")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isNotFound())
        		.andExpect(jsonPath("$.message").exists())
        		.andExpect(jsonPath("$.message").value(equalTo("Envio comunicacao com id 418a2796-16c0-4d62-b682-10624df3f3c5 não encontrado")));
    }
    
    @Test
    @Sql({"/delete_data.sql"})
    public void DELETE_agendamento_informaId_retornaNoContent() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/enviocomunicacao/6ce64a51-1080-490e-96e1-3e779b9b2d0f")
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isNoContent());
    }
    
    @Test
    public void DELETE_agendamento_informaIdInexistente_retornaErro() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/enviocomunicacao/418a2796-16c0-4d62-b682-10624df3f3c5")
    			.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").exists())
				.andExpect(jsonPath("$.message").value(equalTo("Envio comunicacao com id 418a2796-16c0-4d62-b682-10624df3f3c5 não encontrado")));
    }
}
