package com.crio.buildouts.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

public class QuestionDtoTest {

  @Test
  public void testSerialization() throws JsonProcessingException {
    String input = " {\n"
            + "    \"questionId\": \"001\",\n"
            + "    \"title\": \"What is the default IP address of localhost?\",\n"
            + "    \"description\": \"General question\",\n"
            + "    \"type\": \"objective-single\",\n"
            + "    \"correctAnswer\": [\n"
            + "     \"4\"\n"
            + "      ]"
            + "  }";

    new ObjectMapper().readValue(input, QuestionDto.class);
  }
}