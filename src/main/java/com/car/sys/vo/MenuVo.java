package com.car.sys.vo;

import com.car.sys.domain.Menu;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 23:06
 */
public class MenuVo extends Menu {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
