package com.accolite.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	private String isn;
	private String title;
	private String publisher;
	private String[] authors;
	private String publishedDate;

}
