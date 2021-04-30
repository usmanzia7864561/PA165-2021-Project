package muni.pa165.services.config;

import muni.pa165.persistence.PersistenceApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceApplicationContext.class})
@ComponentScan(basePackages = "muni.pa165.services")
public class ServiceConfig { }
