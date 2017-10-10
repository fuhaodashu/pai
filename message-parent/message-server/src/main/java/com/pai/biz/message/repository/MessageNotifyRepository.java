package com.pai.biz.message.repository;

import java.util.List;

import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.message.domain.MessageNotify;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;
/**
 * 对象功能:最大通知记录表 Repository接口
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-07 23:37:41
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface MessageNotifyRepository extends IRepository<String, MessageNotifyPo,MessageNotify>{

	List<MessageNotifyPo> findUsedMessageNotifys(int notifyTimes, MyBatisPage page);
	 
}
