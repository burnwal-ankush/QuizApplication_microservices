package com.ankush.question_service.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name = "Question.findRandomQuestionsByCategory", query = "select q.quesId from Question q where q.category = :category order by random() limit :numQ")
@Entity
@DynamicInsert
@DynamicUpdate
public class Question {
	
	@Id
	@Column(name="ques_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer quesId;
	
	@Column(name="category")
	private String category;
	
	@Column(name="difficulty_level")
	private String difficultyLevel;
	
	@Column(name="option1")
	private String option1;
	
	@Column(name="option2")
	private String option2;
	
	@Column(name="option3")
	private String option3;
	
	@Column(name="option4")
	private String option4;
	
	@Column(name="question_title")
	private String questionTitle;
	
	@Column(name="right_answer")
	private String rightAnswer;

	public Question() {
	}

	public Question(Integer quesId, String category, String difficultyLevel, String option1, String option2, String option3, String option4, String questionTitle, String rightAnswer) {
		this.quesId = quesId;
		this.category = category;
		this.difficultyLevel = difficultyLevel;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.questionTitle = questionTitle;
		this.rightAnswer = rightAnswer;
	}

	public Integer getQuesId() {
		return quesId;
	}

	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
}
