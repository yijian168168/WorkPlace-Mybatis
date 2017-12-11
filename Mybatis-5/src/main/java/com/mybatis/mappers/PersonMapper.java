package com.mybatis.mappers;

import com.mybatis.models.PersonDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangQingrong on 2017/3/27.
 */
@Repository
public interface PersonMapper {

    public int insertPerson(PersonDo personDo);

    public List<PersonDo> queryPerson(PersonDo personDo);

}
