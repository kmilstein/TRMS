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
import TRMS.pojos.EmployeeRole;

public class EmployeeRoleDao implements Dao<EmployeeRole> {

	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionUtil connUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();

	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public Optional<EmployeeRole> get(long id) {
		EmployeeRole role = null;

		try (Connection conn = connUtil.createConnection()) {

			ps = conn.prepareStatement("select * from employee_role where employee_role_id = ? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				long roleId = rs.getLong("employee_role_id");
				String roleName = rs.getString("employee_role_name");
				int responsbility = rs.getInt("employee_role_responsbility");

				role = new EmployeeRole(roleId, roleName, responsbility);
			}

		} catch (SQLException e) {
			log.error("Exception thrown by get method in EmployeeRoleDao");
			e.printStackTrace();
		}

		log.info("Return employee role object");
		return Optional.ofNullable(role);
	}

	@Override
	public List<EmployeeRole> getAll() {
		List<EmployeeRole> roleList = new ArrayList<>();

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement("select * from employee_role");
			rs = ps.executeQuery();

			while (rs.next()) {
				long roleId = rs.getLong("employee_role_id");
				String roleName = rs.getString("employee_role_name");
				int responsbility = rs.getInt("employee_role_responsbility");

				roleList.add(new EmployeeRole(roleId, roleName, responsbility));
			}

		} catch (SQLException e) {
			log.error("Exception thrown by getAll method in EmployeeDao");
			e.printStackTrace();
		}
		
		log.info("Return employee role list");
		return roleList;
	}

	@Override
	public void save(EmployeeRole role) {
		String sql = "insert into employee_role (employee_role_name, employee_role_responsbility) values (?, ?)";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, role.getRoleName());
			ps.setInt(2, role.getRoleResponsibility());
			ps.executeUpdate();

			log.info("Employee Role has been created");
		} catch (SQLException e) {
			log.error("Exception thrown by save method in EmployeeRoleDao");
			e.printStackTrace();
		}

	}

	@Override
	public void update(long id, EmployeeRole role) {
		String sql = "UPDATE employee_role SET employee_role_name = ?, employee_role_responsbility = ? "
				+ "WHERE employee_role_id = ?";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, role.getRoleName());
			ps.setInt(2, role.getRoleResponsibility());
			ps.setLong(3, id);
			ps.executeUpdate();
			
			log.info("Employee role has been updated");
		} catch (SQLException e) {
			log.error("Exception thrown by update method in EmployeeRoleDao");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(EmployeeRole role) {
		String sql = "DELETE FROM employee_role WHERE employee_role_id = ? ";

		try (Connection conn = connUtil.createConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, role.getRoleId());
			ps.executeUpdate();

			log.info("Employee role has been deleted");
		} catch (SQLException e) {
			log.error("Exception thrown by delete method in EmployeeDao");
			e.printStackTrace();
		}
	}
}
