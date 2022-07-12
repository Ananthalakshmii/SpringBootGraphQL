package com.accolite.service;

import java.io.File;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.accolite.model.Book;
import com.accolite.repository.BookRepo;
import com.accolite.service.graphQL.AllBooksDataFetcher;
import com.accolite.service.graphQL.BookDataFetcher;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class BookService {
	
	@Value("classpath:books.graphql")
	private Resource resource;
	
	private GraphQL graphQL;
	@Autowired
	private AllBooksDataFetcher allBookDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;
	@Autowired
	private BookRepo bookRepo;
	
	@PostConstruct
	private void loadSchema() throws Exception{
		
		loadDataIntoSql();
		//get the schema
		File file=resource.getFile();	
		
		//parse the schema
		TypeDefinitionRegistry typeRegistry= new SchemaParser().parse(file);
		RuntimeWiring wiring=buildRuntimeWiring();
		GraphQLSchema schema= new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL=GraphQL.newGraphQL(schema).build();
		
	}

	private void loadDataIntoSql() {
		Stream.of(new Book("123","Book of clouds","Kindle Edition",new String[] {"Chloe"},"Nov 2017"),
				new Book("124","CLoud Arch","Orielly",new String[] {"Peter","Saam"},"Dec 2017"),
				new Book("125","Cloud engineering","Kindle Edition",new String[] {"Harry","Ram"},"Nov 2019"),
				new Book("126","Clouds of IT","Orielly",new String[] {"Binta","RoseMary","Genera"},"Nov 2020")
				)
		.forEach(book->bookRepo.save(book));
		
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("allBooks", allBookDataFetcher)
						.dataFetcher("book", bookDataFetcher)
					)
				.build();
				
	}
	
	public GraphQL getGraphQL() {
		return graphQL;
	}

}
