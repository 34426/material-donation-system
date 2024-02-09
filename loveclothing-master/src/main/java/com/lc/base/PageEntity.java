package com.lc.base;

/**
 * 分页基类
 */
public class PageEntity {

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer limitCount;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

}
