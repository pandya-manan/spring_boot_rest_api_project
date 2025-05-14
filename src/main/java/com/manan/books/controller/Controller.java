package com.manan.books.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manan.books.entities.Book;

@RestController
@RequestMapping("/api/books")
public class Controller {
	
	private final List<Book> books=new ArrayList<>();
	
	private void initialiseBooks()
	{
		books.addAll(List.of(new Book("A to Z Mysteries Absent Autor","Ron Roy","Fiction"), 
				new Book("A to Z Mysteries Orange Outlaw","Ron Roy","Fiction"), 
				new Book("The River Of Adventure","Enid Blyton","Fiction"), 
				new Book("C++","Bjarne Stroustrup","Text Book"), 
				new Book("Programming in Java","Debasis Samanta","Text Book"), 
				new Book("A to Z Mysteries The Lucky Lottery","Ron Roy","Fiction"), 
				new Book("Sherlock Holmes","Arthur Canon Doyle","Crime"), 
				new Book("Murder on the orient express","Agatha Christie","Crime"), 
				new Book("Amar Chitra Katha Shivaji Maharaj","PAI","History"), 
				new Book("Fifty Shades of grey","EL James","Sex and Fantasy")));
		
	}
	
	public Controller()
	{
		initialiseBooks();
	}
	
	@GetMapping
	public List<Book> getAllBooks(@RequestParam(required=false)String category)
	{
		if(category==null)
		{
			return books;
		}
		else
		{
			List<Book> matchingBooks=new ArrayList<>();
			matchingBooks=books.stream().filter(book->book.getCategory().equalsIgnoreCase(category)).toList();
			return matchingBooks;
		}
		
	}

	@GetMapping("/{title}")
	public Book getBookByTitle(@PathVariable String title)
	{
		for(Book book:books)
		{
			if(book.getTitle().equalsIgnoreCase(title))
				return book;
			
		}
		return null;
	}
	
	@PostMapping
	public void addBook(@RequestBody Book newBook)
	{
		boolean isPresent=books.stream().noneMatch(book->book.getTitle().equalsIgnoreCase(newBook.getTitle()));
		if(isPresent)
		{
			books.add(newBook);
		}
		return;
	}
	
	@PutMapping("/{title}")
	public void updateBook(@PathVariable String title,@RequestBody Book updateBook)
	{
		int length=books.size();
		for(int i=0;i<length;i++)
		{
			if(books.get(i).getTitle().equalsIgnoreCase(title))
			{
				books.set(i, updateBook);
			}
		}
		return;
	}
	
	@DeleteMapping("/{title}")
	public void deleteBook(@PathVariable String title)
	{
		books.removeIf(book->book.getTitle().equalsIgnoreCase(title));
		return;
	}
	
	
	
}
