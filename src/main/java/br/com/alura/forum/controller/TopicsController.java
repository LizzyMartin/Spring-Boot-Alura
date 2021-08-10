package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetailTopicDto;
import br.com.alura.forum.controller.dto.TopicDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.controller.form.UpdateTopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping
  public List<TopicDto> list(String courseName) {
    List<Topic> topics;

    if (courseName == null) {
      topics = topicRepository.findAll();
    } else {
      topics = topicRepository.findByCourseName(courseName);
    }

    return TopicDto.convert(topics);

  }

  @PostMapping
  public ResponseEntity<TopicDto> createTopic(@RequestBody @Valid TopicForm form,
      UriComponentsBuilder uriBuilder) {
    Topic topic = form.convert(courseRepository);
    topicRepository.save(topic);

    URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicDto(topic));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetailTopicDto> detail(@PathVariable Long id) {
    Optional<Topic> topic = topicRepository.findById(id);

    return topic.map(value -> ResponseEntity.ok(new DetailTopicDto(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicDto> update(@PathVariable Long id,
      @RequestBody @Valid UpdateTopicForm form) {
    Optional<Topic> optional = topicRepository.findById(id);

    if (optional.isPresent()) {
      Topic topic = form.update(id, topicRepository);
      return ResponseEntity.ok(new TopicDto((topic)));
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Topic> optional = topicRepository.findById(id);

    if (optional.isPresent()) {
      topicRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
  }
}
