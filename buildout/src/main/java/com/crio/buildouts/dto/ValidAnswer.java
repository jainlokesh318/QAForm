package com.crio.buildouts.dto;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidAnswer {
  
  @NotNull
  private String questionId;
  @NotNull
  private String title;
  @NotNull 
  private String description;
  @NotNull
  private String type;
  @NotNull
  private Map<String,String> options;
  @NotNull
  private List<String> userAnswer;
  @NotNull
  private List<String> correct;

  private String explanation;
  @NotNull
  private Boolean answerCorrect;
}
