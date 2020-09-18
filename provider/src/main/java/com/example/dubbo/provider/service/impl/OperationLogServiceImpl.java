package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.OperationLogRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.OperationLogService;
import com.example.dubbo.provider.entity.OperationLog;
import com.example.dubbo.provider.entity.UserInfo;
import com.example.dubbo.provider.mapper.OperationLogMapper;
import com.example.dubbo.provider.repository.OperationLogRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
public class OperationLogServiceImpl implements OperationLogService {

    private static final Logger log = LoggerFactory.getLogger(OperationLogServiceImpl.class);
    @Autowired
    private OperationLogMapper operationLogMapper;
    @Autowired
    private OperationLogRepository operationLogRepository;

    /**
     * 操作日志分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public BaseResponse pageOperationLog(Integer page, Integer size,Integer userId) {//TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page, size);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<OperationLog> operationLogs = null;
        /*if (StringUtils.isNotBlank(role) && StringUtils.isNotBlank(job)) {
            userInfo = userInfoMapper.SelectRoleAndJob(role, job);
        } else if (StringUtils.isNotBlank(role) && !StringUtils.isNotBlank(job)) {
            userInfo = userInfoMapper.SelectRole(role);
        } else if (!StringUtils.isNotBlank(role) && StringUtils.isNotBlank(job)) {
            userInfo = userInfoMapper.SelectJob(job);
        } else {
            userInfo = userInfoMapper.SelecALL();
        }*/
            operationLogs = operationLogMapper.SelecALL();
            PageInfo<OperationLog> pageInfo = new PageInfo(operationLogs);

            response.setData(pageInfo);

        } catch (Exception e) {
            log.error("用户分页查询服务-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public void save(OperationLogRequest operationLogRequest) {
        try {
            if(operationLogRequest!=null) {
                OperationLog operationLog=new OperationLog();
                BeanUtils.copyProperties(operationLogRequest, operationLog);
                operationLogRepository.save(operationLog);
                log.info("新增操作日志：{} ", operationLog);
            }

        }catch (Exception e){
            log.error("新增操作日志-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());

        }

    }


}


