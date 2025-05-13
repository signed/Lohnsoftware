package example.explore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class SpringTest {

  @TestConfiguration
  public static class Configuration {
    @Bean
    public String hello() {
      return "hello";
    }
  }

  @Autowired
  private String hello;

  @Test
  void name() {

  }
}
