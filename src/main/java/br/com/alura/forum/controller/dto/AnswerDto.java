package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Answer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerDto {

  private Long id;
  private String message;
  private LocalDateTime creation;
  private String name;

  public AnswerDto(Answer answer) {
    this.id = answer.getId();
    this.message = answer.getMessage();
    this.creation = answer.getCreation();
    this.name = answer.getName().getName();
  }
}
