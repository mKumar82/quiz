package com.files.entities;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Quiz")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String question;
	private List<Integer> options;
	private int rightAnswer;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	@Transient
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Quiz(int id, String question, List<Integer> options, int rightAnswer, LocalDateTime startDate,
			LocalDateTime endDate, String status) {
		super();
		this.id = id;
		this.question = question;
		this.options = options;
		this.rightAnswer = rightAnswer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Integer> getOptions() {
		return options;
	}

	public void setOptions(List<Integer> options) {
		this.options = options;
	}

	public int getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", question=" + question + ", options=" + options + ", rightAnswer=" + rightAnswer
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}
	
	@PostLoad
    @PostUpdate
    @PostPersist
    private void updateStatus() {
        LocalDateTime now = LocalDateTime.now();
        
        if (now.isBefore(startDate)) {
            status = "inactive";
        } else if (now.isAfter(endDate)) {
            status = "finished";
        } else {
            status = "active";
        }
    }

	
	
	
}
