package TRMS.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TRMS.pojos.Employee;

public class AuthServiceDB implements AuthService {

	private static byte[] salt = new SecureRandom().getSeed(16);
	private static Map<String, String> tokenRepo = new HashMap<>();
	private EmployeeService empService = new EmployeeServiceImpl();

	@Override
	public boolean authenticateUser(String email, String password) {
		List<Employee> empList = empService.findAllEmployees();

		for (Employee emp : empList) {
			if (emp.getEmail().equals(email) && emp.getPassword().equals(password)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String createToken(String email) {
		String token = simpleHash(email);
		tokenRepo.put(token, email);
		return token;
	}

	@Override
	public String validateToken(String token) {
		String tobereturn = tokenRepo.get(token);
		return tokenRepo.get(token);
	}

	private String simpleHash(String username) {

		String hash = null;

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);

			byte[] bytes = md.digest(username.getBytes());

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
			}

			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash;
	}

}
