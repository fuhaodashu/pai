package com.pai.biz.message.repository;

import java.util.List;

import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.message.domain.MessageRecord;
import com.pai.biz.message.persistence.entity.MessageRecordPo;
/**
 * 对象功能:pai_message_record Repository接口
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:11
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface MessageRecordRepository extends IRepository<String, MessageRecordPo,MessageRecord>{

	List<MessageRecordPo> findNoDeadMessages(String status, MyBatisPage page);
}
