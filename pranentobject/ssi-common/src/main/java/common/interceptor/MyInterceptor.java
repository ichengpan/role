package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取session对象
				HttpSession session = request.getSession();
				//获取访问的url
				String requestURI = request.getRequestURI();
				//获取访问参数
				String queryString = request.getQueryString();
				//获取客户端主机
				String remoteHost = request.getRemoteHost();
				
//				System.out.println("#######################");
//				HttpSession session2 = MySessionContext.getSession("287");
//				if (null != session2) {
//					System.out.println(new Gson().toJson(session2));
//				}
//				System.out.println("#######################");
				
				//判断用户是否登陆过
				if (null != session.getAttribute("userInfo")) {
					//已登陆
					return true;
				} else {
					//未登录，重定向页面到登陆页面
					//判断是否是ajax请求
					
					String type = request.getHeader("X-Requested-With");// XMLHttpRequest
					// 重定向
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
					// 转发
					if (StringUtils.equals("XMLHttpRequest", type)) {
						// ajax请求
						response.setHeader("SESSIONSTATUS", "TIMEOUT");
						response.setHeader("CONTEXTPATH", basePath+"index.jsp");
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					} else {
						//常规
						response.sendRedirect(request.getContextPath() + "/index.jsp");
					}
				}
				// true:继续执行;false:返回前台
				return false;
	}
	
}
