package com.accolite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.Book;
import com.accolite.service.BookService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/rest/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		ExecutionResult execute= bookService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute,HttpStatus.OK);
		
	}
	
}
