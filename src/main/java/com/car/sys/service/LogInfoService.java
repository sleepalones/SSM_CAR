package com.car.sys.service;

import com.car.sys.utils.DataGridView;
import com.car.sys.vo.LogInfoVo;

/**
 * @author 铭鸽鸽
 * @create 2020/7/3 22:35
 */
public interface LogInfoService {

    /**
     * 查询所有
     * @param logInfoVo
     * @return
     */
    DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    /**
     * 添加日志
     * @param logInfoVo
     */
    void addLogInfo(LogInfoVo logInfoVo);

    /**
     * 根据id删除日志
     * @param logInfoId
     */
    void deleteLogInfo(Integer logInfoId);

    /**
     * 批量删除日志
     * @param ids
     */
    void deleteBatchLogInfo(Integer[] ids);
}
