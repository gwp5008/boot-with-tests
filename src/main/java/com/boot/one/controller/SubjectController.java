package com.boot.one.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.one.dao.BookDAO;
import com.boot.one.dao.SubjectDAO;
import com.boot.one.model.Book;
import com.boot.one.model.Subject;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectDAO subjectDAO;

	@Autowired
	private BookDAO bookDAO;

	private Book book;
	
	private Subject subject;

	public SubjectController() {

	}

	@GetMapping("/addSubjectPage")
	public String toAddBookPage() {
		return "add-subject-form";
	}

	@GetMapping("/deleteSubjectPage")
	public String toDeleteBookPage() {
		return "delete-subject-form";
	}

	@GetMapping("/searchSubjectsPage")
	public String toSearchBooksPage() {
		return "search-subjects-form";
	}
	@GetMapping("/deleteSubject")
	public String deleteBook(HttpServletRequest request, Model model) {
		subjectDAO.delete(Long.parseLong(request.getParameter("deleteSubjectID")));
		return "delete-subject-success";
	}
	
	@GetMapping("/searchSubjects")
	public String searchForBook(HttpServletRequest request, Model model) {
		String message = "";
		
		String subjectId = request.getParameter("subjectId");
		subject = subjectDAO.find(Long.parseLong(subjectId));
		
		if (subject != null) {
			message = "Your subject was found.";
		}
		else {
			message = "Your subject was not found.";
		}
		model.addAttribute("sSMessage", message);
		
		return "search-subjects-completed";
	}

	@PostMapping("/addSubject")
	public String setSubjectInfo(HttpServletRequest request, Model model) {
		subject = new Subject();

		String subtitle = request.getParameter("subtitle");
		int duration = Integer.parseInt(request.getParameter("duration"));

		subject.setSubtitle(subtitle);
		subject.setDurationInHours(duration);
		subject.setActive(1);
	
		subjectDAO.save(subject);

		return "add-subject-success";
	}
}
