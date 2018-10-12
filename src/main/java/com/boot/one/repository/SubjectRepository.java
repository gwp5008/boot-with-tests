package com.boot.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.one.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
	
	@Modifying
	@Query("update Subject s set s.active = ?1 where s.id = ?2")
	Subject deleteSubject(int activeId, long subjectId);
	
	@Query(value = "select * from Subject s where s.subtitle = ?1", nativeQuery = true)
	Subject checkMatchingSubject(String subjectName);
}

