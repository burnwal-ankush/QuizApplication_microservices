package com.ankush.question_service.service;

import com.ankush.question_service.dao.QuestionDAO;
import com.ankush.question_service.model.Question;
import com.ankush.question_service.model.Response;
import com.ankush.question_service.wrapper.QuestionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionDAO.findAll();
            if (questions.isEmpty()) {
                logger.info("No questions found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching questions", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            List<Question> questions = questionDAO.findByCategory(category);
            if (questions.isEmpty()) {
                logger.info("No questions found for category: {}", category);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching questions by category", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            if (question == null) {
                logger.warn("Invalid question data.");
                return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
            }
            questionDAO.save(question);
            logger.info("Question successfully added.");
            return new ResponseEntity<>("Question created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while saving the question", e);
            return new ResponseEntity<>("Error creating question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        try {
            List<Integer> questionList = questionDAO.findRandomQuestionsByCategory(categoryName, numQuestions);

            return new ResponseEntity<>(questionList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        try {
            List<QuestionWrapper> questionWrapperFromIds = new ArrayList<>();
            List<Question> ques = new ArrayList<>();
            for(Integer id : questionIds)
            {
                ques.add(questionDAO.findById(id).get());
            }
            for(Question q : ques)
            {
                QuestionWrapper wrapper = new QuestionWrapper();
                wrapper.setId(q.getQuesId());
                wrapper.setQuestionTitle(q.getQuestionTitle());
                wrapper.setOption1(q.getOption1());
                wrapper.setOption2(q.getOption2());
                wrapper.setOption3(q.getOption3());
                wrapper.setOption4(q.getOption4());

                questionWrapperFromIds.add(wrapper);
            }
            return new ResponseEntity<>(questionWrapperFromIds, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        try
        {
            for(Response response : responses)
            {
                Question question = questionDAO.findById(response.getId()).get();
                if( response.getResponse().equals(question.getRightAnswer()))
                {
                    right++;
                }
            }
            return new ResponseEntity<>(right, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
