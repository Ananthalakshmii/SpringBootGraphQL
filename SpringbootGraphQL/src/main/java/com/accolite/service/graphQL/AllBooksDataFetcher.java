package com.accolite.service.graphQL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accolite.model.Book;
import com.accolite.repository.BookRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{
	@Autowired
	private BookRepo bookRepo;

	@Override
	public List<Book> get(DataFetchingEnvironment environment) throws Exception {
		return bookRepo.findAll();
	}
	
	
}
