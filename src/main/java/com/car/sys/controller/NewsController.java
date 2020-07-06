package com.car.sys.controller;

import com.car.sys.domain.News;
import com.car.sys.domain.User;
import com.car.sys.service.NewsService;
import com.car.sys.utils.DataGridView;
import com.car.sys.utils.ResultObj;
import com.car.sys.utils.WebUtils;
import com.car.sys.vo.NewsVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 铭鸽鸽
 * @create 2020/7/4 16:56
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * 加载公告列表返回DataGridView
     */
    @RequestMapping("/loadAllNews.do")
    public DataGridView loadAllNews(NewsVo newsVo){
        return newsService.queryAllNews(newsVo);
    }

    /**
     * 根据id查询公告
     */
    @RequestMapping("/loadNewsById.do")
    public News loadNewsById(Integer id){
        return newsService.queryNewsById(id);
    }

    /**
     * 添加公告
     */
    @RequestMapping("/addNews.do")
    public ResultObj addNews(NewsVo newsVo){
        try {
            newsVo.setCreatetime(new Date());
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setOpername(user.getRealname());
            newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     */
    @RequestMapping("/updateNews.do")
    public ResultObj updateNews(NewsVo newsVo) {
        try {
            newsService.updateNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("/deleteNews.do")
    public ResultObj deleteNews(NewsVo newsVo) {
        try {
            newsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     */
    @RequestMapping("/deleteBatchNews.do")
    public ResultObj deleteBatchNews(NewsVo newsVo) {
        try {
            newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}
