package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topic;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TopicDto {

  private Long id;
  private String title;
  private String message;
  private LocalDateTime creation;

  public TopicDto(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.creation = topic.getCreation();
  }

  public static List<TopicDto> convert(final List<Topic> topics) {
    return topics.stream().map(TopicDto::new).collect(Collectors.toList());
  }
}
