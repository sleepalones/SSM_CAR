package com.car.sys.service.impl;

import com.car.sys.dao.LogInfoMapper;
import com.car.sys.domain.LogInfo;
import com.car.sys.service.LogInfoService;
import com.car.sys.utils.DataGridView;
import com.car.sys.vo.LogInfoVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 铭鸽鸽
 * @create 2020/7/3 22:38
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Resource
    private LogInfoMapper logInfoMapper;

    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page = PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> data = logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        logInfoMapper.insertSelective(logInfoVo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoId) {
        logInfoMapper.deleteByPrimaryKey(logInfoId);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer id : ids) {
            logInfoMapper.deleteByPrimaryKey(id);
        }
    }
}
