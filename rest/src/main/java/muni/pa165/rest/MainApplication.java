package muni.pa165.rest;

import muni.pa165.rest.controllers.UserController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackageClasses = UserController.class)
public class MainApplication extends SpringBootServletInitializer {

}
