package com.example.dubbo.provider.service.impl;


import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.OperationLog;
import com.example.dubbo.api.service.OperationLogService;
import com.example.dubbo.provider.dao.OperationLogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@DubboService(version = "1.0.0")
@Service
public class OperationLogServiceImpl implements OperationLogService {

    private static final Logger log = LoggerFactory.getLogger(OperationLogServiceImpl.class);
    @Resource
    private OperationLogDao operationLogDao;

    /**
     * 操作日志分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public BaseResponse pageOperationLog(Integer page, Integer size, Integer userId) {
        PageHelper.startPage(page, size);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            OperationLog operationLog=new OperationLog();
            operationLog.setUserId(userId);
            List<OperationLog> operationLogs = null;
            operationLogs = operationLogDao.queryAll(operationLog);
            PageInfo<OperationLog> pageInfo = new PageInfo(operationLogs);

            response.setData(pageInfo);

        } catch (Exception e) {
            log.error("操作日志分页查询服务-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    /**
     * 操作日志新增
     *
     */
    @Override
    public void save(OperationLog operationLog) {
        try {
            if (operationLog != null) {

                operationLogDao.insert(operationLog);
                log.info("新增操作日志：{} ", operationLog);
            }

        } catch (Exception e) {
            log.error("新增操作日志-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());

        }

    }



}


