package com.ankush.question_service.dao;

import com.ankush.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	List<Integer> findRandomQuestionsByCategory(String category, Integer numQ);

}
