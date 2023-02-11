package jmspring.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
public class HelloRepositoryTest {

    @Autowired
    HelloRepository helloRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("""
                            create table if not exists hello(
                            name varchar(50) primary key,
                            count int
                            )
                """);
    }

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("asdfa")).isNull();
    }

    @Test
    void increaseCount() {
        String name = "jm";
        assertThat(helloRepository.countOf(name)).isEqualTo(0);
        helloRepository.increaseCount(name);
        assertThat(helloRepository.countOf(name)).isEqualTo(1);
        helloRepository.increaseCount(name);
        assertThat(helloRepository.countOf(name)).isEqualTo(2);
    }
}
