package dede.srm.service.interf;

import dede.srm.models.UserToken;
import dede.srm.models.User;

public interface AuthService {
	public String generateHashedPassword(String unhashPassword);
	public User findUserByUsername();
	public User createUser();
	public void deleteUser();
	public User updateUser();
	public UserToken findTokensByUserId();
}
