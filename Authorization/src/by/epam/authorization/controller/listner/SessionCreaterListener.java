package by.epam.authorization.controller.listner;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.epam.authorization.entity.User;

/**
 * SessionCreaterListener.java
 * Class implements interface HttpSessionListener
 * Creates session with status guests for new users
 * @author MasSword
 */

public class SessionCreaterListener implements HttpSessionListener{
	private static final String LOGIN = "login";

	/**
     * Method creates for new users session with object of user.class and "guest" status
     * @param HttpSessionEvent ses 
     */
	
	@Override
	public void sessionCreated(HttpSessionEvent ses) {
		HttpSession session = ses.getSession();
		String userStatus = "guest";
		User currentUser = new User();
		currentUser.setRole(userStatus);
        synchronized (session) {
            session.setAttribute(LOGIN, currentUser);
        }
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent session) {}
}
