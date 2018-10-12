package com.boot.one.dao.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.one.dao.SubjectDAO;
import com.boot.one.model.Book;
import com.boot.one.model.Subject;
import com.boot.one.repository.SubjectRepository;

public class SubjectDAOTest {
	
	@InjectMocks
	private SubjectDAO subjectDAO;
	
	@Mock
	private SubjectRepository subjectRepo;
	
	private Subject subject = new Subject();
	private ArrayList<Book> listBooks = new ArrayList<Book>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSave() {
		subject.setSubjectId(1);
		subject.setDurationInHours(1);
		subject.setSubtitle("Test Subtitle");
		subject.setBooks(listBooks);
		subject.setActive(1);
		
		when(subjectRepo.save(subject)).thenReturn(subject);
		
		assertThat(subjectDAO.save(subject), is(instanceOf(Subject.class)));
	}
	
	@Test
	public void testFind() {
		subject.setSubjectId(1);
		subject.setDurationInHours(1);
		subject.setSubtitle("Test Subtitle");
		subject.setBooks(listBooks);
		subject.setActive(1);
		
		when(subjectRepo.findOne(1l)).thenReturn(subject);
		Subject testSubject = subjectDAO.find(1l);
		
		assertEquals(1l, testSubject.getSubjectId());
	}
	
	@Test
	public void testDelete() {
		subject.setSubjectId(1);
		subject.setDurationInHours(1);
		subject.setSubtitle("Test Subtitle");
		subject.setBooks(listBooks);
		subject.setActive(0);
		
		when(subjectRepo.deleteSubject(0, subject.getSubjectId())).thenReturn(subject);
		
		assertEquals(subjectDAO.delete(subject.getSubjectId()).getActive(), 0);
	}
	
	@Test
	public void testCheckMatchingSubject() {
		subject.setSubjectId(1);
		subject.setDurationInHours(1);
		subject.setSubtitle("Test Subtitle");
		subject.setBooks(listBooks);
		subject.setActive(1);
		
		when(subjectRepo.checkMatchingSubject(subject.getSubtitle())).thenReturn(subject);
		
		assertThat(subjectDAO.checkMatchingSubject(subject.getSubtitle()), is(instanceOf(Subject.class)));
	}
}
