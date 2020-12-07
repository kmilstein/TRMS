package TRMS.pojos;

public class EmployeeRole {
	
	private long roleId;
	private String roleName;
	private int roleResponsibility;
	
	public EmployeeRole() {
		super();
	}

	public EmployeeRole(String roleName, int roleResponsibility) {
		super();
		this.roleName = roleName;
		this.roleResponsibility = roleResponsibility;
	}

	public EmployeeRole(long roleId, String roleName, int roleResponsibility) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleResponsibility = roleResponsibility;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleResponsibility() {
		return roleResponsibility;
	}

	public void setRoleResponsibility(int roleResponsibility) {
		this.roleResponsibility = roleResponsibility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (roleId ^ (roleId >>> 32));
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + roleResponsibility;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRole other = (EmployeeRole) obj;
		if (roleId != other.roleId)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (roleResponsibility != other.roleResponsibility)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeRole [roleId=" + roleId + ", roleName=" + roleName + ", roleResponsibility="
				+ roleResponsibility + "]";
	}	
}
