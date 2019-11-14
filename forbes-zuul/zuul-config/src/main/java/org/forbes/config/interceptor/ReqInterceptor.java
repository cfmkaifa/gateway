package org.forbes.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/****
 * ReqInterceptor概要说明：自定义请求拦截器
 * @author Huanghy
 */
public class ReqInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOGGER = LoggerFactory.getLogger(ReqInterceptor.class);

	
	/**完成后执行方法
	 */
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		LOGGER.info("Interceptor afterCompletion method is running !");
        super.afterCompletion(request, response, handler, ex);
    }
}
