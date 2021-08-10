package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.TopicRepository;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateTopicForm {

  @NotNull
  @NotEmpty
  @Length(min = 5)
  private String title;

  @NotNull
  @NotEmpty
  @Length(min = 10)
  private String message;

  public Topic update(Long id, TopicRepository topicRepository) {
    Topic topic = topicRepository.getById(id);
    topic.setTitle(this.title);
    topic.setMessage(this.message);

    return topic;
  }
}
