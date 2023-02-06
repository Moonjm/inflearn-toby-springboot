package jmspring.config.autoconfig;

import jmspring.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@MyAutoConfiguration
public class DispatcherServletConfig {

    @Bean
    DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
