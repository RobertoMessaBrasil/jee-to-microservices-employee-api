package robertomessabrasil.jeetoms.employee.api.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public EmployeeDao employeeDao() {

        return new EmployeeJDBC();

    }

}
