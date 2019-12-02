package org.forbes.biz;

import java.util.List;

import org.forbes.comm.model.SysPermission;
import org.forbes.comm.model.SysUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/***
 * ISysUserService概要说明：用户管理
 * @author Huanghy
 */
@ConditionalOnProperty(name="spring.application.usercenter")
@FeignClient(name="${spring.application.usercenter}")
public interface ISysUserService {

	
	
	/****获取用户
	 * getUserByName方法慨述:
	 * @param username
	 * @return SysUser
	 * @创建人 huanghy
	 * @创建时间 2019年11月15日 下午4:05:22
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	@RequestMapping(value="/user/user-by-name",method=RequestMethod.GET)
	SysUser getUserByName(@RequestParam(name="username",required=true)String username);
	
	
	
	/***
	 * getRole方法慨述:获取角色
	 * @param username
	 * @return List<String>
	 * @创建人 huanghy
	 * @创建时间 2019年11月15日 下午4:22:37
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	@RequestMapping(value="/user/role-by-name",method=RequestMethod.GET)
	List<String>  getRole(@RequestParam(name="username",required=true)String username);
	
	
	
	/***
	 * queryByUser方法慨述:查询当前用户所拥有权限
	 * @param username
	 * @return List<SysPermission>
	 * @创建人 huanghy
	 * @创建时间 2019年11月15日 下午4:26:25
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	@RequestMapping(value="/user/permission-by-name",method=RequestMethod.GET)
	List<SysPermission> queryByUser(@RequestParam(name="username",required=true)String username);
}
