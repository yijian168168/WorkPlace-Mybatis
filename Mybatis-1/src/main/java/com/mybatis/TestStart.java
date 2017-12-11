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
        billTest.setId(1L);
        billTest.setValue("test-1");
        billTestService.updateBillTest(billTest);
    }

    @Test
    public void test2(){
        BillTest billTest = new BillTest();
        billTest.setId(1L);
        billTestService.selectBillTestList(billTest);
    }
}
