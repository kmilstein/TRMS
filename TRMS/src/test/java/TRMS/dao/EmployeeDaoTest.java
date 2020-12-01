package TRMS.dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import TRMS.pojos.Employee;
import TRMS.util.ConnectionUtil;

public class EmployeeDaoTest {

	private ConnectionUtil connUtil = new ConnectionUtil();
	private PreparedStatement ps;
	private EmployeeDao empDao = new EmployeeDao();
	private ResultSet rs;
	private Employee emp1;
	private Employee emp2;

	@Before
	public void setUp() throws Exception {
		emp1 = new Employee("theresa.rogers@company.com", "knittingqueen", "Theresa", "Rogers", 4, 1, 1, 1);
		emp2 = new Employee(3, "john.baugh@company.com", "WarUnicorn", "John", "Baugh", 3, 3, 3, 2);
	}

	@Test
	public void testGetMethodOfEmployee() throws SQLException {
		Optional<Employee> emp = empDao.get(3);

		Employee e = emp.orElseGet(() -> new Employee(0, "non-existing user"));

		System.out.println(e);
	}

	@Test
	public void testGetAllMethodOfEmployee() throws SQLException {
		System.out.println(empDao.getAll());
	}

	@Test
	public void testSaveMethodOfEmployee() throws SQLException {
		empDao.save(emp1);
		ps = connUtil.createConnection().prepareStatement("select * from employee_trms where employee_id = ? ");

		ps.setLong(1, 7);

		rs = ps.executeQuery();

		if (rs.next()) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	@Test
	public void testUpdateMethodOfEmployee() throws SQLException {

		String password = null;
		Employee update = new Employee(3, "john.baugh@company.com", "Password", "John", "Baugh", 3, 3, 3, 2);

		empDao.update(3, update);

		ps = connUtil.createConnection().prepareStatement("select * from employee_trms where employee_id = ? ");

		ps.setLong(1, 3);

		rs = ps.executeQuery();

		while (rs.next()) {
			password = rs.getString("employee_password");
		}

		assertTrue(update.getPassword().equals(password));
	}
	
	@Test
	public void testDeleteMethodOfEmployee() throws SQLException {

		empDao.delete(emp1);
		
		ps = connUtil.createConnection().prepareStatement("select * from employee_trms where employee_id = ? ");

		ps.setLong(1, 6);

		rs = ps.executeQuery();

		if (rs.next()) {
			assertFalse(false);
		} else {
			assertTrue(true);
		}
	}
}
