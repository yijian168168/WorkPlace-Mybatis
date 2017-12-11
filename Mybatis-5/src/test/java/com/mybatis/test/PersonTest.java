package com.mybatis.test;

import com.mybatis.mappers.PersonMapper;
import com.mybatis.models.PersonDo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ZhangQingrong on 2017/3/27.
 */
public class PersonTest {


    private PersonMapper personMapper;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        personMapper = applicationContext.getBean(PersonMapper.class);
    }

    @Test
    public void test1(){
        PersonDo personDo = new PersonDo();
        personDo.setId("4");
        personDo.setName("qingrong");
        personDo.setAge("25");
        personMapper.insertPerson(personDo);
        List<PersonDo> personDos = personMapper.queryPerson(personDo);
        System.out.println(personDos);
    }
}
