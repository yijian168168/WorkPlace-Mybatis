package com.myabatis.test;

import com.mybatis.mapper.read.PersonReadMapper;
import com.mybatis.mapper.write.PersonWriteMapper;
import com.mybatis.model.PersonDo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZhangQingrong on 2017/3/27.
 */
public class PersonTest {

    private PersonReadMapper personReadMapper;

    private PersonWriteMapper personWriteMapper;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        personReadMapper = applicationContext.getBean(PersonReadMapper.class);
        personWriteMapper = applicationContext.getBean(PersonWriteMapper.class);
    }

    @Test
    public void test1(){
        PersonDo personDo = new PersonDo();
        personDo.setId("1");
        personDo.setName("qingrong");
        personDo.setAge("25");
        personWriteMapper.insertPerson(personDo);
        PersonDo readPersonDo = personReadMapper.queryPerson(personDo);
        System.out.println(readPersonDo);

    }
}
