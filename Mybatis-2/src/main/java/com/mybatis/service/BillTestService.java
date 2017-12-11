package com.mybatis.service;

import com.mybatis.mapper.BillTestMapper;
import com.mybatis.model.BillTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhangQingrong on 2017/3/20.
 */
@Repository
public class BillTestService {

    @Autowired
    private BillTestMapper billTestMapper;

    @Transactional(rollbackForClassName = "java.lang.Exception")
    public int updateBillTest(BillTest billTest) throws Exception {
        System.out.println("start Transactional" + billTest);
        billTestMapper.selectBillTest(billTest);
        System.out.println("select for update success " + billTest);
//        throw new Exception("test");
        int result = billTestMapper.updateBillTest(billTest);
        System.out.println("update result :  " + result);
        return result;
    }
}
