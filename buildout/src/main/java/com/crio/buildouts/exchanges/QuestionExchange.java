package com.crio.buildouts.exchanges;

import com.crio.buildouts.dto.QuestionDto;

import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionExchange {

  @NotNull
  private List<QuestionDto> questions;
}