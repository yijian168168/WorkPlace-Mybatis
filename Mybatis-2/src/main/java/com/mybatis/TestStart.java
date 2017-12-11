package com.mybatis;

import com.mybatis.model.BillTest;
import com.mybatis.service.BillTestService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZhangQingrong on 2017/3/20.
 */
public class TestStart {

    private BillTestService billTestService ;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        billTestService = applicationContext.getBean(BillTestService.class);
    }
    @Test
    public void test(){
        BillTest billTest = new BillTest();
        billTest.setStat("true");
//        billTest.setStat("false");
        billTest.setId(2L);
        billTest.setValue("test-2");
        try {
            billTestService.updateBillTest(billTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
