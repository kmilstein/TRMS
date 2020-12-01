package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import TRMS.util.ConnectionUtil;
import TRMS.pojos.Employee;

public class EmployeeDao implements Dao<Employee> {

	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionUtil connUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();
	
	

	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public Optional<Employee> get(long id) {
		Employee emp = null;

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from employee_trms where employee_id = ? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				long empId = rs.getLong("employee_id");
				String email = rs.getString("employee_email");
				String password = rs.getString("employee_password");
				String firstName = rs.getString("employee_first_name");
				String lastName = rs.getString("employee_last_name");
				int role = rs.getInt("employee_role");
				long superId = rs.getLong("employee_supervisor_id");
				long deptHeadId = rs.getLong("employee_dept_head_id");
				long benCoId = rs.getLong("employee_ben_co_id");

				emp = new Employee(empId, email, password, firstName, lastName, role, superId, deptHeadId, benCoId);
			}

		} catch (SQLException e) {
			log.error("Exception thrown by get method in EmployeeDao");
			e.printStackTrace();
		}

		log.info("Return employee object");
		return Optional.ofNullable(emp);
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> empList = new ArrayList<>();

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from employee_trms");
			rs = ps.executeQuery();

			while (rs.next()) {
				long empId = rs.getLong("employee_id");
				String email = rs.getString("employee_email");
				String password = rs.getString("employee_password");
				String firstName = rs.getString("employee_first_name");
				String lastName = rs.getString("employee_last_name");
				int role = rs.getInt("employee_role");
				long superId = rs.getLong("employee_supervisor_id");
				long deptHeadId = rs.getLong("employee_dept_head_id");
				long benCoId = rs.getLong("employee_ben_co_id");

				empList.add(
						new Employee(empId, email, password, firstName, lastName, role, superId, deptHeadId, benCoId));
			}

		} catch (SQLException e) {
			log.error("Exception thrown by getAll method in EmployeeDao");
			e.printStackTrace();
		}

		log.info("Return employee list");
		return empList;
	}

	@Override
	public void save(Employee emp) {
		String sql = "insert into employee_trms (employee_email, employee_password, employee_first_name, "
				+ "employee_last_name, employee_role, employee_supervisor_id, employee_dept_head_id, "
				+ "employee_ben_co_id) values (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setString(1, emp.getEmail());
			ps.setString(2, emp.getPassword());
			ps.setString(3, emp.getFirstName());
			ps.setString(4, emp.getLastName());
			ps.setInt(5, emp.getRole());
			ps.setLong(6, emp.getSupervisorId());
			ps.setLong(7, emp.getDeptHeadId());
			ps.setLong(8, emp.getBenCoId());

			ps.executeUpdate();
			log.info("Employee has been created");
		} catch (SQLException e) {
			log.error("Exception thrown by save method in EmployeeDao");
			e.printStackTrace();
		}
	}

	@Override
	public void update(long id, Employee emp) {
		String sql = "UPDATE employee_trms SET employee_email =?, employee_password =?, employee_first_name = ?, "
				+ "employee_last_name = ?, employee_role = ?, employee_supervisor_id = ?, employee_dept_head_id = ?, "
				+ "employee_ben_co_id = ? WHERE employee_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setString(1, emp.getEmail());
			ps.setString(2, emp.getPassword());
			ps.setString(3, emp.getFirstName());
			ps.setString(4, emp.getLastName());
			ps.setInt(5, emp.getRole());
			ps.setLong(6, emp.getSupervisorId());
			ps.setLong(7, emp.getDeptHeadId());
			ps.setLong(8, emp.getBenCoId());
			ps.setLong(9, id);

			ps.executeUpdate();
			log.info("Employee has been updated");
		} catch (SQLException e) {
			log.error("Exception thrown by update method in EmployeeDao");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Employee emp) {
		String sql = "DELETE FROM employee_trms WHERE employee_email = ? ";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getEmail());
			ps.executeUpdate();

			log.info("Employee has been deleted");
		} catch (SQLException e) {
			log.error("Exception thrown by delete method in EmployeeDao");
		}
	}
}
