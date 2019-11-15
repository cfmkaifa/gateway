package org.forbes.comm.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Table: f_sys_permission
 */
@Data
public class SysPermission  implements Serializable  {
   
	private static final long serialVersionUID = 6430290986069297694L;

	/**
     * 父级id
     * Table:     f_sys_permission
     * Column:    parent_id
     * Nullable:  true
     */
    private Integer parentId;

    /**
     * 资源名称
     * Table:     f_sys_permission
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * 菜单权限编码
     * Table:     f_sys_permission
     * Column:    perms
     * Nullable:  true
     */
    private String perms;

    /**
     * 组件
     * Table:     f_sys_permission
     * Column:    component
     * Nullable:  true
     */
    private String component;

    /**
     * 组件名称
     * Table:     f_sys_permission
     * Column:    component_name
     * Nullable:  true
     */
    private String componentName;

    /**
     * 是否聚合子路由
     * Table:     f_sys_permission
     * Column:    always_show
     * Nullable:  true
     */
    private String alwaysShow;

    /**
     * 是否路由
     * Table:     f_sys_permission
     * Column:    is_route
     * Nullable:  true
     */
    private String isRoute;

    /**
     * Table:     f_sys_permission
     * Column:    is_leaf
     * Nullable:  true
     */
    private String isLeaf;

    /**
     * 是否隐藏
     * Table:     f_sys_permission
     * Column:    is_hidden
     * Nullable:  true
     */
    private String isHidden;

    /**
     * Table:     f_sys_permission
     * Column:    sort_no
     * Nullable:  true
     */
    private Integer sortNo;

    /**
     * 描述
     * Table:     f_sys_permission
     * Column:    description
     * Nullable:  true
     */
    private String description;

    /**
     * 路由请求地址
     * Table:     f_sys_permission
     * Column:    redirect
     * Nullable:  true
     */
    private String redirect;

    /**
     * 后台请求地址
     * Table:     f_sys_permission
     * Column:    url
     * Nullable:  true
     */
    private String url;

    /**
     * 菜单图标
     * Table:     f_sys_permission
     * Column:    icon
     * Nullable:  true
     */
    private byte[] icon;
}