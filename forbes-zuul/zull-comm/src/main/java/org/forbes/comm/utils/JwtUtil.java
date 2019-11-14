package org.forbes.comm.utils;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.exception.ForbesException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author hhy
 * @create 2018-07-12 14:23
 * @desc JWT工具类
 **/
public class JwtUtil {

	// 过期时间30分钟
	public static final long EXPIRE_TIME = 30 * 60 * 1000;
	public static final long CODE_EXPIRE_TIME = 5 * 60 * 1000;
	private static final String E_PREFIX = "#{";
	private static final String E_SUFFIX = "}";

	/**
	 * 校验token是否正确
	 *
	 * @param token  密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String username, String secret) {
		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim(CommonConstant.USER_NAME, username).build();
			// 效验TOKEN
			verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 *
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim(CommonConstant.USER_NAME).asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成签名,5min后过期
	 *
	 * @param username 用户名
	 * @param secret   用户的密码
	 * @return 加密的token
	 */
	public static String sign(String username, String secret) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		// 附带username信息
		return JWT.create()
				.withClaim(CommonConstant.USER_NAME, username)
				.withExpiresAt(date).sign(algorithm);

	}

	/**
	 * 根据request中的token获取用户账号
	 * 
	 * @param request
	 * @return
	 * @throws JeecgBootException
	 */
	public static String getUserNameByToken(HttpServletRequest request) throws ForbesException {
		String accessToken = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
		String username = getUsername(accessToken);
		if (ConvertUtils.isEmpty(username)) {
			throw new ForbesException("未获取到用户");
		}
		return username;
	}
	
	/**
	  *  从session中获取变量
	 * @param key
	 * @return
	 */
	public static String getSessionData(String key) {
		//得到${} 后面的值
		String moshi = CommonConstant.DEFAULT_EMPTY;
		if(key.indexOf(E_SUFFIX)!=-1){
			 moshi = key.substring(key.indexOf(E_SUFFIX)+1);
		}
		String returnValue = null;
		if (key.contains(E_PREFIX)) {
			key = key.substring(2,key.indexOf(E_SUFFIX));
		}
		if (ConvertUtils.isNotEmpty(key)) {
			HttpSession session = SpringContextUtils.getHttpServletRequest().getSession();
			returnValue = (String) session.getAttribute(key);
		}
		//结果加上${} 后面的值
		if(returnValue!=null){returnValue = returnValue + moshi;}
		return returnValue;
	}
}
