package ohtu.services;

import ohtu.domain.User;
import java.lang.Character;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
    	if (username.length() < 3 || password.length() < 8) {
        	return true;
        }
        
        boolean usernameInvalid = false;
        for (int i = 0; i < username.length(); i++) {
			if (!Character.isLetter(username.charAt(i)) || !Character.isLowerCase(username.charAt(i))) {
        		usernameInvalid = true;
        	}
        }

        boolean passwordInvalid = true;
        for (int i = 0; i < password.length(); i++) {
        	if (!Character.isLetter(password.charAt(i))) {
        		passwordInvalid = false;
        	}
        }
        
        return usernameInvalid || passwordInvalid;
    }
}
