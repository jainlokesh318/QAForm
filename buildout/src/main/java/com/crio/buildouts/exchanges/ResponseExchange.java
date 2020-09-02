package com.crio.buildouts.exchanges;

import com.crio.buildouts.dto.ValidAnswer;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExchange {
  private List<ValidAnswer> validAnswers;
  private Map<String, Integer> summary;
}