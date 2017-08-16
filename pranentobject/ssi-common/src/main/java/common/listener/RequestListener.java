package common.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextListener;

public class RequestListener extends RequestContextListener{
	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		HttpServletRequest servletRequest = (HttpServletRequest) requestEvent.getServletRequest();
		System.out.println("request监听：这是Request(请求)的监听，发送的请求是---"+servletRequest.getRequestURI());
	}
}
