package com.car.sys.controller;

import com.car.sys.domain.LogInfo;
import com.car.sys.service.LogInfoService;
import com.car.sys.utils.DataGridView;
import com.car.sys.utils.ResultObj;
import com.car.sys.vo.LogInfoVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 铭鸽鸽
 * @create 2020/7/3 22:48
 */
@RestController
@RequestMapping("/logInfo")
public class LogInfoController {

    @Resource
    private LogInfoService logInfoService;

    /**
     * 加载日志列表返回DataGridView
     */
    @RequestMapping("/loadAllLogInfo.do")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo){
        return logInfoService.queryAllLogInfo(logInfoVo);
    }

    /**
     * 删除日志
     */
    @RequestMapping("/deleteLogInfo.do")
    public ResultObj deleteLogInfo(LogInfoVo logInfoVo){
        try {
            logInfoService.deleteLogInfo(logInfoVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     */
    @RequestMapping(value = "/deleteBatchLogInfo.do",method = RequestMethod.POST)
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo){
        try {
            logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}
