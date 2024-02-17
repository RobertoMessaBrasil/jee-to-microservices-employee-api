package robertomessabrasil.jeetoms.employee.api.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import robertomessabrasil.jeetoms.employee.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeJDBC implements EmployeeDao {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeJDBC.class);

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Employee> getEmployees() {

        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;

        try {
            con = getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String stm = "Select * from employee";

        List<Employee> records = new ArrayList<>();

        try {

            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                records.add(employee);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return records;
    }

    public Connection getConnection() throws ClassNotFoundException {

        Connection con = null;

        try {

            con = DriverManager.getConnection(
                    this.url,
                    this.userName,
                    this.password);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage());
        } finally {
        }

        return con;

    }

}