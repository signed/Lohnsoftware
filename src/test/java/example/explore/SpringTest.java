package example.explore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"""
    org.example.value=Hello
  """})
public class SpringTest {

  @TestConfiguration
  public static class Configuration {
    @Bean
    public String hello() {
      return "Hello from the Configuration";
    }
  }

  @Autowired
  private String hello;

  @Value("${org.example.value}")
  private String value;

  @Test
  void overridingConfiguration() {
    assertThat(value).isEqualTo("Hello");
    assertThat(hello).isEqualTo("Hello from the Configuration");
  }
}
