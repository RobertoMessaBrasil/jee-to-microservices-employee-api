package robertomessabrasil.jeetoms.employee.api.model;

import robertomessabrasil.jeetoms.employee.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployees();

}
