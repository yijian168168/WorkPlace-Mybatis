package com.mybatis.mapper;


import com.mybatis.dataSource.DataSource;
import com.mybatis.dataSource.DynamicDataSourceGlobal;
import com.mybatis.model.PersonDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangQingrong on 2017/3/27.
 */
@Repository
public interface PersonMapper {

    @DataSource(DynamicDataSourceGlobal.WRITE)
    public int insertPerson(PersonDo personDo);
    @DataSource(DynamicDataSourceGlobal.READ)
    public List<PersonDo> queryPerson(PersonDo personDo);

}
