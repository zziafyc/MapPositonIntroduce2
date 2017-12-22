package com.zhongyong.mappositonintroduce.api;

import java.io.Serializable;

/**
 * Created by fyc on 2017/12/22.
 */

public class Body implements Serializable {
    private int ret_code;
    private PageBean pagebean;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public PageBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(PageBean pagebean) {
        this.pagebean = pagebean;
    }
}
