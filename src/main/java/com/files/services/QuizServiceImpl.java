package com.files.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.files.dao.QuizDao;
import com.files.entities.Quiz;



@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	public QuizDao quizDao;

	@Override
	public List<Quiz> getAllQuizzes() {
		List<Quiz> list = (List<Quiz>) quizDao.findAll();
		return list;
	}

	@Override
	public String addQuiz(Quiz quizDto) {
		// Create a new Quiz entity
        Quiz quiz = new Quiz();
        quiz.setQuestion(quizDto.getQuestion());
        quiz.setOptions(quizDto.getOptions());
        quiz.setRightAnswer(quizDto.getRightAnswer());
        quiz.setStartDate(quizDto.getStartDate());
        quiz.setEndDate(quizDto.getEndDate());
        quiz.setStatus(quizDto.getStatus());

        quizDao.save(quiz);
        return "quiz added successfullly";
	}

	@Override
	public List<Quiz> getActiveQuiz() {
		 LocalDateTime now = LocalDateTime.now();
		 List<Quiz> activeQuiz = quizDao.findActiveQuiz(now);
		    
		    if (activeQuiz == null) {
		     return null;
		    }
		    
		    List<Quiz> activeQuizDto = mapQuizToDto(activeQuiz);
		    System.out.println(activeQuizDto);
		    return activeQuizDto;
			}		

	private List<Quiz> mapQuizToDto(List<Quiz> quizzes) {
		List<Quiz> quizDtoList = new ArrayList<>();
	    
	    for (Quiz quiz : quizzes) {
	        Quiz quizDto = new Quiz();
	        quizDto.setId(quiz.getId());
	        quizDto.setQuestion(quiz.getQuestion());
	        quizDto.setOptions(quiz.getOptions());
	        quizDto.setRightAnswer(quiz.getRightAnswer());
	        quizDto.setStartDate(quiz.getStartDate());
	        quizDto.setEndDate(quiz.getEndDate());
	        quizDto.setStatus(quiz.getStatus());
	        
	        quizDtoList.add(quizDto);
	    }
	    
	    return quizDtoList;
	    
	}
	


	@Override
	public String getQuizResult(int id) {
		Optional<Quiz> optionalQuiz = quizDao.findById(id);
		    
	    if (optionalQuiz.isEmpty()) {
	      return "no results";
	    }
	    
	    Quiz quiz = optionalQuiz.get();
	    
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime quizEndTime = quiz.getEndDate().plusMinutes(5);
	    
	    if (now.isBefore(quizEndTime)) {
	       return "result will be available 5 min after quiz ends";
	    }	    
	    
	    int rightAnswer = quiz.getRightAnswer();
	    
	    return "The right answer is: " + rightAnswer;
	}
	

}			
