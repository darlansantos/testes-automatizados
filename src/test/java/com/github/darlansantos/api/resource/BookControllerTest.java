package com.github.darlansantos.api.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.darlansantos.api.dto.BookDTO;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	static String BOOK_API = "/api/books";

	@Autowired
	MockMvc mvc;
	
	@Test
	@DisplayName("Deve criar um livro com sucesso.")
	public void createBookTest() throws Exception {
		
		BookDTO bookDTO = BookDTO.builder().author("Paulo").title("As aventuras").isbn("001").build();
		String json = new ObjectMapper().writeValueAsString(bookDTO);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(BOOK_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc
			.perform(request)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("id").isNotEmpty())
			.andExpect(jsonPath("title").value(bookDTO.getTitle()))
			.andExpect(jsonPath("author").value(bookDTO.getAuthor()))
			.andExpect(jsonPath("isbn").value(bookDTO.getIsbn()));
	}
	
	@Test
	@DisplayName("Deve lançar erro de validação quando não houver dados suficientes do livro.")
	public void createInvalidBookTest() {
		
	}

}
