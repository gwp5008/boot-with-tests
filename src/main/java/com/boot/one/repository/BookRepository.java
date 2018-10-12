package com.boot.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.one.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Modifying
	@Query("update Book b set b.active = ?1 where b.id = ?2")
	Book deleteBook(int activeId, long bookId);
}
