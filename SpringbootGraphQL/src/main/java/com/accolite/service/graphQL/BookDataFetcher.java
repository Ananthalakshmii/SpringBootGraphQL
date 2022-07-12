package com.accolite.service.graphQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accolite.model.Book;
import com.accolite.repository.BookRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book>{
	@Autowired
	private BookRepo bookRepo;

	@Override
	public Book get(DataFetchingEnvironment environment) throws Exception {
		String isn=environment.getArgument("id");
		return bookRepo.findById(isn).get();
	}

}
