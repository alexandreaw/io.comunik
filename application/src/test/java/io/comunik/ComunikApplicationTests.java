package io.comunik;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

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
    @Sql({"/get_data.sql"})
    public void GET_consultaStatus_informaId_retornaStatusAgendamento() throws JsonProcessingException, Exception {
    	var comunicacaoEntity = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
    	
    	mockMvc.perform(get("/enviocomunicacao/status/c68f204c-7d9b-47de-b2ad-c19da3db2a92")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(comunicacaoEntity))
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.id").exists())
        		.andExpect(jsonPath("$.status").exists())
        		.andExpect(jsonPath("$.status").value(equalTo("ENVIADO")));
    }
    
    @Test
    @Sql({"/delete_data.sql"})
    public void DELETE_agendamento_informaId_retornaNoContent() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/enviocomunicacao/6ce64a51-1080-490e-96e1-3e779b9b2d0f")
    			.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isNoContent());
    }
}
