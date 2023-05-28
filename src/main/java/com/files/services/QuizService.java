package com.files.services;

import java.util.List;

import com.files.entities.Quiz;

public interface QuizService {
		public List<Quiz> getAllQuizzes();
		public String addQuiz(Quiz quiz);
		public List<Quiz> getActiveQuiz();
		public String getQuizResult(int id);
}
