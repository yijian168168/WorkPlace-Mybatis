package com.mybatis.model;

/**
 * Created by ZhangQingrong on 2017/3/20.
 */
public class BillTest {

    private Long id;

    private String value;

    private String stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "BillTest{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", stat='" + stat + '\'' +
                '}';
    }
}
