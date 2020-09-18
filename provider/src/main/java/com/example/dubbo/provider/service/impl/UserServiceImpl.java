package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.UserInfo;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.provider.common.BeanUtills;
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
//@DubboService(version = "1.0.0")
//@Path("user")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoMapper userInfoMapper;

  @Override
//    @Path("login")
//    @GET
    public BaseResponse login(String username,String password) {
        BaseResponse response=new BaseResponse();
        try {
            com.example.dubbo.provider.entity.UserInfo userInfo=userInfoRepository.findByUsername(username);
            if(userInfo!=null){
                if(userInfo.getPassword().equals(password)){
                    response.setData(StatusCode.Success);
                    response.setMsg("success");
                }
                else{
                    response.setData(StatusCode.Fail);
                    response.setMsg("密码错误");
                }
            }
            else if(userInfo==null){
                response.setData(StatusCode.Fail);
                response.setMsg("用户不存在");
            }

        }catch (Exception e){
            log.error("用户登录发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    /**
     *根据用户名查询用户role
     */

    @Override
    public String findByUserName(String usernamne) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            com.example.dubbo.provider.entity.UserInfo infos=userInfoRepository.findByUsername(usernamne);
            log.info("根据用户名查询到的用户role：{} ",infos);
           return infos.getId()+"_"+infos.getRole();

        }catch (Exception e){
            log.error("根据用户名查询到的用户role实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return null;
    }


    @Override
//    @Path("pageUser")
    public BaseResponse pageUser(Integer page, Integer size, String role) {
        //TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page,size);
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<com.example.dubbo.provider.entity.UserInfo> userInfo=null;
            if(StringUtils.isNotBlank(role)){
               userInfo=  userInfoMapper.SelectRole(role);
            }
            else{
                userInfo = userInfoMapper.SelecALL();
            }
            PageInfo<com.example.dubbo.provider.entity.UserInfo> pageInfo = new PageInfo(userInfo);

            response.setData(pageInfo);

        }catch (Exception e){
            log.error("用户分页查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    @Override
//    @Path("insert")
    public BaseResponse insertUser(UserInfo user) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(user!=null) {
                com.example.dubbo.provider.entity.UserInfo userInfo=new com.example.dubbo.provider.entity.UserInfo();
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
//    @Path("delete")
    public BaseResponse deletByID(Integer id) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            com.example.dubbo.provider.entity.UserInfo user=userInfoRepository.findById(id);
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
//    @Path("update")
    public BaseResponse updateUser(UserInfo user) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(user.getId()!=null) {
                com.example.dubbo.provider.entity.UserInfo userInfo=userInfoRepository.findById(user.getId());
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