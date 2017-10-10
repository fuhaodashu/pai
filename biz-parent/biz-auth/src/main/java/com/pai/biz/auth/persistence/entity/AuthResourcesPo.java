package com.pai.biz.auth.persistence.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pai.base.db.persistence.entity.TreeType;

/**
 * 对象功能:资源 entity对象
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-08-07 14:07:40
 */
public class AuthResourcesPo extends AuthResourcesTbl implements TreeType<AuthResourcesPo>{

	private static final long serialVersionUID = 7464811014677516464L;

	private List<AuthResourcesPo> subs = new ArrayList<AuthResourcesPo>();
	
	//判断角色是否拥有该资源（1=是，2=否）
	private Integer roleStatus;
	
	@Override
	public List<AuthResourcesPo> getSubs() {
		return Collections.unmodifiableList(subs);
	}

	@Override
	public void addSub(AuthResourcesPo po) {
		subs.add(po);
	}
	
	//对字段type枚举判断，菜单用于查询显示，按钮用于权限拦截
	public static enum ResourceTypeEnum{
		MENU(1), //菜单
		BUTTON(2); //按钮
		private ResourceTypeEnum(Integer type){
			this.type = type;
		}
		private Integer type;
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}
	
}