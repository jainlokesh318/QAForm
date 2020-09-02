package com.crio.buildouts.service;

import com.crio.buildouts.dto.QuestionDto;
import com.crio.buildouts.dto.Responses;
import com.crio.buildouts.exchanges.ResponseExchange;

import java.util.List;

public interface QuestionService {
  void putQuestion(List<QuestionDto> questionDto);

  List<QuestionDto> getQuestions();

  ResponseExchange validateResponse(List<Responses> list);
}