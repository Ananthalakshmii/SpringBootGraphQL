package com.accolite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootGraphQlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGraphQlApplication.class, args);
	}

}

/*
 
 {
    allBooks{
        isn
        title
        authors
    }
    book(id: "123"){
        isn
        title
    }
}
 
 */
