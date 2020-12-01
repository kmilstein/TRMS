package TRMS.service;

public interface AuthService {

	public boolean authenticateUser(String email, String password);

	public String createToken(String email);

	public String validateToken(String token);

}
