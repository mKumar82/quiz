package com.files.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.files.entities.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{
	
		@Query("SELECT q FROM Quiz q WHERE :now BETWEEN q.startDate AND q.endDate")
		public List<Quiz> findActiveQuiz(@Param("now") LocalDateTime now); 
}
