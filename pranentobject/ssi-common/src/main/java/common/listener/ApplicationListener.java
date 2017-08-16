package common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("服务器监听：在服务器启动的时候能监听到");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
