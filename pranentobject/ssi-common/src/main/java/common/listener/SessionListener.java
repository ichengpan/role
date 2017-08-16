package common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String id = se.getSession().getId();
		System.out.println("session监听：创建了一个session会话---" + id);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}

}
