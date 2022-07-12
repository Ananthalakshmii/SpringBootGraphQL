package com.accolite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.model.Book;

public interface BookRepo extends JpaRepository<Book, String>{

}
