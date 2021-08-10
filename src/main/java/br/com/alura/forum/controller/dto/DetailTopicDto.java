package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.TopicStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetailTopicDto {

  private Long id;
  private String title;
  private String message;
  private LocalDateTime creation;
  private String name;
  private TopicStatus status;
  private List<AnswerDto> answers;

  public DetailTopicDto(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.creation = topic.getCreation();
    this.name = topic.getUser().getName();
    this.status = topic.getStatus();
    this.answers = new ArrayList<>();

    this.answers.addAll(
        topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
  }

}
