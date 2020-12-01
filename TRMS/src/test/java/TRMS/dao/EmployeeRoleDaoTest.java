package TRMS.dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import TRMS.pojos.EmployeeRole;
import TRMS.util.ConnectionUtil;

public class EmployeeRoleDaoTest {

	private ConnectionUtil connUtil = new ConnectionUtil();
	private PreparedStatement ps;
	private EmployeeRoleDao roleDao = new EmployeeRoleDao();
	private ResultSet rs;
	private EmployeeRole role1;
	private EmployeeRole role2;

	@Before
	public void setUp() throws Exception {
		role1 = new EmployeeRole(5, "new role", 4);
		role2 = new EmployeeRole(4, "Benefits Coordinator", 3);
	}

	@Test
	public void testGetMethodOfEmployee() throws SQLException {
		Optional<EmployeeRole> emp = roleDao.get(4);

		EmployeeRole r = emp.orElseGet(() -> new EmployeeRole("non-existing role", 0));

		assertEquals(role2, r);
	}

	@Test
	public void testGetAllMethodOfEmployee() throws SQLException {

		System.out.println(roleDao.getAll());
	}

	@Test
	public void testSaveMethodOfEmployee() throws SQLException {
		roleDao.save(role1);
		ps = connUtil.createConnection().prepareStatement("select * from employee_role where employee_role_id = ? ");

		ps.setLong(1, 4);

		rs = ps.executeQuery();

		if (rs.next()) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	@Test
	public void testUpdateMethodOfEmployee() throws SQLException {

		String roleName = null;
		EmployeeRole update = new EmployeeRole(5, "CEO", 4);

		roleDao.update(5, update);

		ps = connUtil.createConnection().prepareStatement("select * from employee_role where "
				+ "employee_role_id = ? ");

		ps.setLong(1, update.getRoleId());

		rs = ps.executeQuery();

		while (rs.next()) {
			roleName = rs.getString("employee_role_name");
		}

		assertTrue(update.getRoleName().equals(roleName));
	}

	@Test
	public void testDeleteMethodOfEmployee() throws SQLException {

		roleDao.delete(role1);

	}
}
