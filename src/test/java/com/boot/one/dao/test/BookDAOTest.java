package com.boot.one.dao.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.one.dao.BookDAO;
import com.boot.one.model.Book;
import com.boot.one.repository.BookRepository;

public class BookDAOTest {
	
	@InjectMocks
	private BookDAO bookDAO;
	
	@Mock
	private BookRepository bookRepo;
	
	private Book book = new Book();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSave() {
		book.setBookId(1);
		book.setPrice(1D);
		book.setPublishDate("01/01/2018");
		book.setTitle("Test Title");
		book.setVolume(1);
		book.setActive(1);
		
		when(bookRepo.save(book)).thenReturn(book);
		
		assertThat(bookDAO.save(book), is(instanceOf(Book.class)));
	}
	
	@Test
	public void testFind() {
		book.setBookId(1);
		book.setPrice(1D);
		book.setPublishDate("01/01/2018");
		book.setTitle("Test Title");
		book.setVolume(1);
		book.setActive(1);
		
		when(bookRepo.findOne(1l)).thenReturn(book);
		Book testBook = bookDAO.find(1l);
		
		assertEquals(1l, testBook.getBookId());
	}
	
	@Test
	public void testDelete() {
		book.setBookId(1);
		book.setPrice(1D);
		book.setPublishDate("01/01/2018");
		book.setTitle("Test Title");
		book.setVolume(1);
		book.setActive(0);
		
		when(bookRepo.deleteBook(0, book.getBookId())).thenReturn(book);
		
		assertEquals(bookDAO.delete(book.getBookId()).getActive(), 0);
	}
}
