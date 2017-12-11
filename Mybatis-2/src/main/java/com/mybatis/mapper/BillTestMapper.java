package com.mybatis.mapper;

import com.mybatis.model.BillTest;
import org.springframework.stereotype.Repository;

/**
 * Created by ZhangQingrong on 2017/3/20.
 */
@Repository
public interface BillTestMapper {

    public int updateBillTest(BillTest billTest);

    public BillTest selectBillTest(BillTest billTest);
}
