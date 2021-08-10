package br.com.alura.forum.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String message;
  private LocalDateTime creation = LocalDateTime.now();
  @Enumerated(EnumType.STRING)
  private TopicStatus status = TopicStatus.NAO_RESPONDIDO;

  @ManyToOne
  private User user;
  @ManyToOne
  private Course course;
  @OneToMany(mappedBy = "topic")
  @ToString.Exclude
  private List<Answer> answers = new ArrayList<>();

  public Topic() {
  }

  public Topic(String title, String message, Course course) {
    this.title = title;
    this.message = message;
    this.course = course;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Topic other = (Topic) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
