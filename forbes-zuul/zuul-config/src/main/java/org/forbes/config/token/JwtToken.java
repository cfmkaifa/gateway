package org.forbes.config.token;

import org.apache.shiro.authc.AuthenticationToken;
/***
 * JwtToken概要说明：证书
 * @author Huanghy
 */
public class JwtToken implements AuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	private String token;
 
    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}
