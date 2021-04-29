package muni.pa165.services.config;

import muni.pa165.persistence.PersistenceApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Taken from eshop project from github
 */
@Configuration
@Import({PersistenceApplicationContext.class})
@ComponentScan(basePackages = "muni.pa165.services")
public class ServiceConfig {
    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }
}
