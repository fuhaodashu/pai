package com.pai.biz.member;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2017-06-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:/conf/member-server.xml")
@Transactional
public abstract class BasicTest {

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 得到spring上下文对象
     *
     * @return
     */
    public final ApplicationContext getApplicationContext() {

        return applicationContext;
    }

}
