package com.ankush.question_service.controller;

import com.ankush.question_service.model.Question;
import com.ankush.question_service.model.Response;
import com.ankush.question_service.service.QuestionService;
import com.ankush.question_service.wrapper.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return questionService.getAllQuestions();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        try {
            return questionService.getQuestionsByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question) {
        try {
            return questionService.addQuestion(question);
        } catch (
                Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/generate")
    public ResponseEntity<List<Integer>> generateQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
        try {
            return questionService.getQuestionsForQuiz(categoryName, numQuestions);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds)
    {
        try
        {
            return questionService.getQuestionFromId(questionIds);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        try
        {
            return questionService.getScore(responses);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
