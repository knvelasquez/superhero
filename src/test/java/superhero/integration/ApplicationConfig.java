package superhero.integration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfig.class)
@EnableJpaRepositories(basePackages = {"com.*"})
public class ApplicationConfig {
}
