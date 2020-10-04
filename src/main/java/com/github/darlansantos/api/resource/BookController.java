package com.github.darlansantos.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.darlansantos.api.dto.BookDTO;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO create() {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setAuthor("Author");
		bookDTO.setTitle("Meu Livro");
		bookDTO.setIsbn("1213212");
		bookDTO.setId(1L);
		return bookDTO;
	}

}
