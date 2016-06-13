package by.epam.authorization.controller.listner;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.epam.authorization.entity.User;

public class SessionCreaterListener implements HttpSessionListener{
	private static final String LOGIN = "login";

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
