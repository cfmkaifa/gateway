package org.zull.comm.exception;
/***
 * ForbesException概要说明：
 * @author Huanghy
 */
public class ForbesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ForbesException(String message){
		super(message);
	}
	
	public ForbesException(Throwable cause)
	{
		super(cause);
	}
	
	public ForbesException(String message,Throwable cause)
	{
		super(message,cause);
	}
}