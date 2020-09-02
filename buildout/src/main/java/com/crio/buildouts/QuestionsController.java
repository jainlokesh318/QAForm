package com.crio.buildouts;

import com.crio.buildouts.dto.QuestionDto;
import com.crio.buildouts.dto.UserResponse;
import com.crio.buildouts.exchanges.QuestionExchange;
import com.crio.buildouts.exchanges.ResponseExchange;
import com.crio.buildouts.model.Question;
import com.crio.buildouts.service.QuestionService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuestionsController {
    
  @Autowired
  private QuestionService questionService;

  @PutMapping("/1")
  public void putQuestion(@RequestBody List<QuestionDto> questionDto) {
    questionService.putQuestion(questionDto);
  }

  @GetMapping("/1")
  public QuestionExchange getQuestions() {
    return new QuestionExchange(questionService.getQuestions());
  }

  @PostMapping("/1")
  public  ResponseExchange validateAnswers(@RequestBody UserResponse response) {
    return questionService.validateResponse(response.getResponses());
  }
}