package services;

import models.User;

public class Login {
	public static boolean login(User user, String password) {
		
		if (user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}
