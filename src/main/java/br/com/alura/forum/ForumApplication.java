package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("br.com.alura.forum.model")
public class ForumApplication {

  public static void main(String[] args) {
    SpringApplication.run(ForumApplication.class, args);
  }

}
