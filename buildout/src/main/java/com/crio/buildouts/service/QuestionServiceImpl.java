package com.crio.buildouts.service;

import com.crio.buildouts.dto.QuestionDto;
import com.crio.buildouts.dto.Responses;
import com.crio.buildouts.dto.ValidAnswer;
import com.crio.buildouts.exchanges.ResponseExchange;
import com.crio.buildouts.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

  /*
   * private QuestionRepository questionRepository;
   * 
   * public String saveQuestion(List<QuestionDto> questionDto, String moduleId) {
   * Question question = Question.builder() .options(questionDto.getOptions())
   * .correctAnswer(questionDto.getCorrectAnswer()) //TODO :- change
   * implementation to List of String .description(questionDto.getDescription())
   * .questionId(questionDto.getQuestionId()) .title(questionDto.getTitle())
   * .type(questionDto.getType()) .moduleId(moduleId) .build();
   * 
   * questionRepository.save(question);
   * 
   * return question.getQuestionId(); }
   */

  @Autowired
  private MongoTemplate mongoTemplate;

  // function to put questions into database
  public void putQuestion(List<QuestionDto> questions) {
    mongoTemplate.dropCollection(Question.class);

    for (QuestionDto question : questions) {
      mongoTemplate.save(question, "Question");
    }

    System.out.println("Question put");
    System.out.println(questions);
  }

  public List<QuestionDto> getQuestions() {
    List<QuestionDto> formList = new ArrayList<>();
    try {
      List<Question> formEntityList = mongoTemplate.findAll(Question.class);

      for (Question entity : formEntityList) {
        QuestionDto questionDto = new ModelMapper().map(entity, QuestionDto.class);
        formList.add(questionDto);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return formList;
  }

  @Override
  public ResponseExchange validateResponse(List<Responses> responses) {
    
    List<ValidAnswer> validAnswers = new ArrayList<>();
    Map<String, Integer> summary = new HashMap<>(); 

    int point = 0;

    for (Responses response : responses) {
      String qid = response.getQuestionId();
      
      Query query = new Query()
          .addCriteria(Criteria.where("questionId").is(qid));   
      
      //get all the data of a particular id from DB
      List<Question> list = mongoTemplate.find(query, Question.class);

      ValidAnswer validAnswerObject = new ValidAnswer();
      validAnswerObject.setQuestionId(list.get(0).getQuestionId());
      validAnswerObject.setTitle(list.get(0).getTitle());
      validAnswerObject.setDescription(list.get(0).getDescription());
      validAnswerObject.setType(list.get(0).getType());
      validAnswerObject.setOptions(list.get(0).getOptions());
      validAnswerObject.setCorrect(list.get(0).getCorrectAnswer());

      validAnswerObject.setUserAnswer(response.getUserResponses());
      validAnswerObject.setExplanation(null);
      
      if (list.get(0).getCorrectAnswer().equals(response.getUserResponses())) {
        validAnswerObject.setAnswerCorrect(true);
        point++;
      } else {
        validAnswerObject.setAnswerCorrect(false);
      }
      validAnswers.add(validAnswerObject);
    }

    summary.put("score", point);
    summary.put("total", responses.size());

    ResponseExchange responseExchangeObject = new ResponseExchange();
    responseExchangeObject.setValidAnswers(validAnswers);
    responseExchangeObject.setSummary(summary);

    return responseExchangeObject;
  }

}