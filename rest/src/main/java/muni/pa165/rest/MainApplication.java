package muni.pa165.rest;

import muni.pa165.rest.controllers.AuthController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackageClasses = AuthController.class)
public class MainApplication extends SpringBootServletInitializer {

}
