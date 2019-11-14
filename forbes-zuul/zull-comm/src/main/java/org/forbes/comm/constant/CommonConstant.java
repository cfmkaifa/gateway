package org.forbes.comm.constant;
/***
 * CommonConstant概要说明：常用常量
 * @author Huanghy
 */
public interface CommonConstant {
	
	
	/*******http状态码********/
	public  static  final String  ACESS_ORIGIN = "Access-control-Allow-Origin";
	public  static  final String  ACESS_METHODS = "Access-Control-Allow-Methods";
	public  static  final String  ACESS_HEADERS = "Access-Control-Allow-Headers";
	public  static  final String  ORIGIN = "Origin";
	public  static  final String  METHODS = "GET,POST,OPTIONS,PUT,DELETE";
	public  static  final String  HEADERS = "Access-Control-Request-Headers";
	
	
	public  static  final Integer NOT_LOGIN_CODE = 501;
	public  static  final String  CONTENT_TYPE_CODE = "Content-Type";
	public  static  final String  CONTENT_TYPE_VAL = "application/json;charset=UTF-8";
	public  static final  String  X_ACCESS_TOKEN = "X-Access-Token";
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public  static final  Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public  static final  Integer SC_OK_200 = 200;
    /****/
    /****默认空值
     */
    public static String DEFAULT_EMPTY = "";
    public static final String USER_NAME = "username";
}
