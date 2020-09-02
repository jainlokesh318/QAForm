package com.crio.buildouts.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "Question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

  @Id
  private String moduleId;

  @Column
  private String questionId;

  @Column
  private String title;
  @Column
  private String description;
  @Column
  private String type;
  @Column
  private Map<String, String> options;
  @Column
  private List<String> correctAnswer;
    
}