package br.com.alura.forum.config.validation;

import lombok.Getter;

@Getter
public class FormErrorDto {

  private String field;
  private String error;

  public FormErrorDto(String field, String error) {
    this.field = field;
    this.error = error;
  }
}
