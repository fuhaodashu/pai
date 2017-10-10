package com.pai.biz.auth.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.helper.PasswordHelper;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.string.StringCollections;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.session.OnlineUserIdHolder;
import com.pai.biz.auth.persistence.dao.AuthUserDao;
import com.pai.biz.auth.persistence.entity.AuthRoleUserPo;
import com.pai.biz.auth.persistence.entity.AuthUserPo;
import com.pai.biz.auth.repository.AuthRoleUserRepository;
import com.pai.biz.frame.domain.AbstractDomain;

/**
 * 对象功能:用户 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class AuthUser extends AbstractDomain<String, AuthUserPo>{
	 
	private AuthUserDao authUserDao = null;
	
	@Resource
	private IdGenerator idGenerator;

	@Resource
	private AuthRoleUserRepository authRoleUserRepository;
	
	protected void init(){
		authUserDao = SpringHelper.getBean(AuthUserDao.class);
		setDao(authUserDao);
	}

	public void updatePassword(AuthUserPo authUserPo) {
		authUserPo.setPassword(PasswordHelper.getEncryptPassword(authUserPo.getName()+authUserPo.getPassword()));
		authUserDao.updatePassword(authUserPo);
	}

	public void save(AuthUserPo authUserPo) {
		if(StringUtils.isNotEmpty(authUserPo.getPassword())){
			authUserPo.setPassword(PasswordHelper.getEncryptPassword(authUserPo.getName()+authUserPo.getPassword()));
		}
		ArrayList<String> roleIds = new ArrayList<String>(StringCollections.toList(authUserPo.getRoleIds(), ";"));
		if(StringUtils.isEmpty(authUserPo.getId())){
			super.setData(authUserPo);
			super.save();
			//用户-角色
			addRoleUser(authUserPo, roleIds);
		}else {
			authUserDao.updateByExampleSelective(authUserPo, b().a("whereSql", "id_="+authUserPo.getId()).p());
			//用户-角色
			List<AuthRoleUserPo> authRoleUserPos = authRoleUserRepository.findByUserId(authUserPo.getId());
			for(AuthRoleUserPo authRoleUserPo : authRoleUserPos){
				if (roleIds.contains(authRoleUserPo.getRoleId())) {
					//排除保留的角色
					roleIds.remove(authRoleUserPo.getRoleId());
				}else{
					//删除原来的多余用户-角色关联
					authRoleUserRepository.newInstance().destroy(authRoleUserPo.getId());
				}
			}
			//新增新加的角色
			addRoleUser(authUserPo, roleIds);
		}
			
	}

	private void addRoleUser(AuthUserPo authUserPo, List<String> roleIds) {
		for (String roleId : roleIds) {
			AuthRoleUserPo authRoleUserPo = new AuthRoleUserPo();
			authRoleUserPo.setRoleId(roleId);
			authRoleUserPo.setUserId(authUserPo.getId());
			authRoleUserPo.setCreateBy(OnlineUserIdHolder.getUserId());
			authRoleUserPo.setCreateTime(new Date());
			authRoleUserRepository.newInstance(authRoleUserPo).save();
		}
	}	 
	 
}
