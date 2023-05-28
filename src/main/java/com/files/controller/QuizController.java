package com.files.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.files.advice.ErrorHandler;
import com.files.entities.Quiz;
import com.files.services.QuizService;

@RestController
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@GetMapping("/quizzes/all")
	public ResponseEntity<?> getAllQuizzes()
	{
		try {
	        List<Quiz> quizzes = quizService.getAllQuizzes();
	        return ResponseEntity.ok(quizzes);
	    } catch (Exception ex) {
	    	String errorMessage = "Failed to retrieve quizzes";
	        return errorHandler.handleException(ex,errorMessage);
	    }
	}	
	
	@GetMapping("/quizzes/active")
	public ResponseEntity<?> getActiveQuiz()
	{
		
		try {
	        
			List<Quiz> quiz = this.quizService.getActiveQuiz();
	        return ResponseEntity.ok(quiz==null?"no active quiz":quiz);
	        
	    } catch (Exception ex) {
	    	System.out.println(ex);
	    	String errorMessage = "Failed to retrieve active quiz";
	        return errorHandler.handleException(ex,errorMessage);
	    }
	}
	
	@GetMapping("/quizzes/{id}/result")
	public ResponseEntity<?> getQuizResult(@PathVariable String id)
	{
		try {
			String response = this.quizService.getQuizResult(Integer.parseInt(id));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
		 	String errorMessage = "Failed to retrieve result";
	        return errorHandler.handleException(ex,errorMessage);
	    }
	}
	
	@PostMapping("/quizzes")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
	{
		
		try {
			String response = this.quizService.addQuiz(quiz);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			String errorMessage = "Failed to add quiz";
	        return errorHandler.handleException(ex,errorMessage);
		}
	}

}
