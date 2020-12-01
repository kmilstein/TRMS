package TRMS.pojos;

public class Employee {
	
	private long employeeId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int role;
	private long supervisorId;
	private long deptHeadId;
	private long benCoId;
	
	public Employee() {
		super();
	}

	public Employee(long employeeId, String email) {
		super();
		this.employeeId = employeeId;
		this.email = email;
	}

	public Employee(String email, String password, String firstName, String lastName, int role,
			long supervisorId, long deptHeadId, long benCoId) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.supervisorId = supervisorId;
		this.deptHeadId = deptHeadId;
		this.benCoId = benCoId;
	}
	
	

	public Employee(long employeeId, String email, String password, String firstName, String lastName, int role,
			long supervisorId, long deptHeadId, long benCoId) {
		super();
		this.employeeId = employeeId;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.supervisorId = supervisorId;
		this.deptHeadId = deptHeadId;
		this.benCoId = benCoId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public long getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(long deptHeadId) {
		this.deptHeadId = deptHeadId;
	}

	public long getBenCoId() {
		return benCoId;
	}

	public void setBenCoId(long benCoId) {
		this.benCoId = benCoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (benCoId ^ (benCoId >>> 32));
		result = prime * result + (int) (deptHeadId ^ (deptHeadId >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + role;
		result = prime * result + (int) (supervisorId ^ (supervisorId >>> 32));
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
		Employee other = (Employee) obj;
		if (benCoId != other.benCoId)
			return false;
		if (deptHeadId != other.deptHeadId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (supervisorId != other.supervisorId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", email=" + email + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", role=" + role + ", supervisorId=" + supervisorId
				+ ", deptHeadId=" + deptHeadId + ", benCoId=" + benCoId + "]\n";
	}
}
