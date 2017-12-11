package com.mybatis.mapper.read;

import com.mybatis.model.PersonDo;
import org.springframework.stereotype.Repository;

/**
 * Created by ZhangQingrong on 2017/3/27.
 */
@Repository
public interface PersonReadMapper {

    public PersonDo queryPerson(PersonDo personDo);

}
