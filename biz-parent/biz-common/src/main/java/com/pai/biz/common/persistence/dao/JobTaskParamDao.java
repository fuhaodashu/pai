package com.pai.biz.common.persistence.dao;
import com.pai.base.db.persistence.dao.IDao;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 Dao接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 */
public interface JobTaskParamDao extends IDao<String, JobTaskParamPo> {

}
