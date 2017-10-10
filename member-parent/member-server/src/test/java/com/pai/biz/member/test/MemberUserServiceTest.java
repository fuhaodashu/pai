package com.pai.biz.member.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.test.BaseTestCase;
import com.pai.biz.member.api.model.MemberUserBean;
import com.pai.biz.member.api.service.MemberUserService;

/**
 * 测试类，。
 * @author FU_HAO
 *
 */
@ContextConfiguration({"classpath:conf/member-server.xml"})
public class MemberUserServiceTest extends BaseTestCase{
	
	@Resource
    private IdGenerator idGenerator;
    
    @Resource
    private MemberUserService memberUserService;
    
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    
    @Test
	public void listMemberUserServiceTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		BaseResponse<ResPage<MemberUserBean>> baseResponse = memberUserService.listMemberUserService(map, 1, 10);
		System.out.println(JSON.toJSONString(baseResponse, true));
	}
    
    @Test
    public void testThread(){
    	final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
//    	Lock lock = new ReentrantLock();
    	List<String> list = new ArrayList<String>();
    	for (int i = 0; i < 1000; i++){
    		list.add("a"+i);
    	}
//    	System.out.println(list);
    	for (final String str : list) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					if(taskExecutor.getActiveCount() < taskExecutor.getMaxPoolSize()) {
						System.out.println(Thread.currentThread().getName()+"--->"+str);
					}
				}
			});
		}
    }
}
