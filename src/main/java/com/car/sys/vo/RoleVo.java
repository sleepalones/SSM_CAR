package com.car.sys.vo;

import com.car.sys.domain.Role;

/**
 * @author 铭鸽鸽
 * @create 2020/7/2 23:03
 */
public class RoleVo extends Role {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 接收多个角色id
     */
    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

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
