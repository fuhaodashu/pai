package com.pai.biz.common.test;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.test.BaseTestCase;

/**
 * 测试基类，。
 * 其下的测试类均继承该子类
 * @author FUHAO
 *
 */
@ContextConfiguration({"classpath:conf/pai-common-test.xml"})
public class JobTaskLogBaseTest extends BaseTestCase{
	
	@Resource
    protected IdGenerator idGenerator;
}
