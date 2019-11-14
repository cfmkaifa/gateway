package org.forbes.config.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.vo.Result;
import org.forbes.config.token.JwtToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

	
	
	/**
	 * 执行登录认证
	 *
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
			try {
				executeLogin(request, response);
//				String url = getPathWithinApplication(request);
//				boolean isPermitted =  SecurityUtils.getSubject().isPermitted(url);
//				if(!isPermitted){
//					throw new ChaseException("你没有权限访问此接口!");
//				}
				return true;
			} catch (Exception e) {
				if(e instanceof ForbesException){
					throw new AuthenticationException(e.getMessage());
				} else {
					throw new AuthenticationException(e.getMessage());
				}
			}
	}

	/**
	 *
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(CommonConstant.X_ACCESS_TOKEN);
		JwtToken jwtToken = new JwtToken(token);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}

	/**
	 * 对跨域提供支持
	 * @throws IOException 
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws IOException {
		HttpServletResponse httpServletResponse = null;
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.setHeader(CommonConstant.ACESS_ORIGIN, httpServletRequest.getHeader(CommonConstant.ORIGIN));
			httpServletResponse.setHeader(CommonConstant.ACESS_METHODS,CommonConstant.METHODS);
			httpServletResponse.setHeader(CommonConstant.ACESS_HEADERS, httpServletRequest.getHeader(CommonConstant.HEADERS));
			// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
			if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
				httpServletResponse.setStatus(HttpStatus.OK.value());
				return false;
			}
			return super.preHandle(request, response);
		}catch(Exception e){
			log.error(e.getMessage());
			Result<?> result = new Result<>();
			if(e instanceof AuthenticationException){
				result = Result.error(CommonConstant.NOT_LOGIN_CODE, e.getMessage());
			} else {
				result.error500(e.getMessage());
			}
			httpServletResponse.setHeader(CommonConstant.CONTENT_TYPE_CODE,CommonConstant.CONTENT_TYPE_VAL);
			httpServletResponse.getWriter().write(JSON.toJSONString(result));
			httpServletResponse.getWriter().flush();
			return false;
		}
	}
}
