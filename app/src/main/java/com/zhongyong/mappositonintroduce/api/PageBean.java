package com.zhongyong.mappositonintroduce.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fyc on 2017/12/22.
 */

public class PageBean implements Serializable {

    private int allPages;
    private List<Content> contentlist;
    private int currentPage;
    private int allNum;
    private int maxResult;

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public List<Content> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<Content> contentlist) {
        this.contentlist = contentlist;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }
}
