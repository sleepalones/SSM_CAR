package com.car.sys.service;

import com.car.sys.domain.News;
import com.car.sys.utils.DataGridView;
import com.car.sys.vo.NewsVo;

/**
 * @author 铭鸽鸽
 * @create 2020/7/4 16:46
 */
public interface NewsService {

    /**
     * 查询所有公告
     * @param newsVo
     * @return
     */
    DataGridView queryAllNews(NewsVo newsVo);

    /**
     * 添加公告
     * @param newsVo
     */
    void addNews(NewsVo newsVo);

    /**
     * 修改公告
     * @param newsVo
     */
    void updateNews(NewsVo newsVo);

    /**
     * 根据id删除公告
     * @param newsId
     */
    void deleteNews(Integer newsId);

    /**
     * 批量删除公告
     * @param ids
     */
    void deleteBatchNews(Integer[] ids);

    /**
     * 查询一个公告
     * @param id
     * @return
     */
    News queryNewsById(Integer id);
}
