package com.example.dubbo.provider.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.UserInfoRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.provider.common.BeanUtills;
import com.example.dubbo.provider.entity.UserInfo;
import com.example.dubbo.provider.mapper.UserInfoMapper;
import com.example.dubbo.provider.repository.UserInfoRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public BaseResponse findUserList() {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<UserInfo> infos=userInfoRepository.findAll();
            log.info("查询到的用户列表数据：{} ",infos);
            response.setData(infos);

        }catch (Exception e){
            log.error("用户列表查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    @Override
    public BaseResponse pageUser(Integer page, Integer size, String role, String job) {
        //TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page,size);
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<UserInfo> userInfo=null;
            if(StringUtils.isNotBlank(role)&&StringUtils.isNotBlank(job)){
               userInfo=  userInfoMapper.SelectRoleAndJob(role,job);
            }
            else if(StringUtils.isNotBlank(role)&&!StringUtils.isNotBlank(job)){
                userInfo= userInfoMapper.SelectRole(role);
            }
            else if(!StringUtils.isNotBlank(role)&&StringUtils.isNotBlank(job)){
                userInfo= userInfoMapper.SelectJob(job);
            }
            else{
                userInfo = userInfoMapper.SelecALL();
            }
            PageInfo<UserInfo> pageInfo = new PageInfo(userInfo);

            response.setData(pageInfo);

        }catch (Exception e){
            log.error("用户分页查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    @Override
    public BaseResponse insertUser(UserInfoRequest user) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(user!=null) {
                UserInfo userInfo=new UserInfo();
                BeanUtils.copyProperties(user, userInfo);
               userInfoRepository.save(userInfo);
                log.info("新增用户：{} ", userInfo);
            }

        }catch (Exception e){
            log.error("新增用户-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse deletByID(Integer id) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            UserInfo user=userInfoRepository.findById(id);
            if(user!=null) {
                userInfoRepository.delete(user);
                log.info("删除用户：{} ", user);
            }

        }catch (Exception e){
            log.error("删除用户-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse updateUser(UserInfoRequest user) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(user.getId()!=null) {
                UserInfo userInfo=userInfoRepository.findById(user.getId());
                BeanUtills.copyProperties(user, userInfo);
                userInfoRepository.save(userInfo);
                log.info("更新用户：{} ", userInfo);
            }

        }catch (Exception e){
            log.error("更新用户-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }


}