package com.mybatis.mapper;

import com.mybatis.model.BillTest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ZhangQingrong on 2017/3/20.
 */
@Component
public interface BillTestMapper {

    public int updateBillTest(BillTest billTest);

    public BillTest selectBillTest(BillTest billTest);

    public List<BillTest> selectBillTestList(BillTest billTest);
}
