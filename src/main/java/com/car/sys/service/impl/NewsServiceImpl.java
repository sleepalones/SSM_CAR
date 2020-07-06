package com.car.sys.service.impl;

import com.car.sys.dao.NewsMapper;
import com.car.sys.domain.News;
import com.car.sys.service.NewsService;
import com.car.sys.utils.DataGridView;
import com.car.sys.vo.NewsVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 铭鸽鸽
 * @create 2020/7/4 16:49
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<Object> page = PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<News> data = newsMapper.queryAllNews(newsVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addNews(NewsVo newsVo) {
        newsMapper.insertSelective(newsVo);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    @Override
    public void deleteNews(Integer newsId) {
        newsMapper.deleteByPrimaryKey(newsId);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer id : ids) {
            newsMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public News queryNewsById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
