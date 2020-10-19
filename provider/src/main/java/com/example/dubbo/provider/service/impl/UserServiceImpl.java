package com.example.dubbo.provider.service.impl;


import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.UserInfo;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.provider.common.BeanUtills;
import com.example.dubbo.provider.dao.UserInfoDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)

@DubboService(version = "1.0.0")
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserInfoDao userInfoDao;



    @Override
    public BaseResponse login(String username,String password) {
        BaseResponse response=new BaseResponse();
        try {
            UserInfo userInfo=userInfoDao.queryByUsername(username);
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
            UserInfo infos=userInfoDao.queryByUsername(usernamne);
            log.info("根据用户名查询到的用户role：{} ",infos);
            return infos.getId()+"_"+infos.getRole();

        }catch (Exception e){
            log.error("根据用户名查询到的用户role实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return null;
    }

    @Override
    public BaseResponse pageUser(Integer page, Integer size, String role) {
        //TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page,size);
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            UserInfo user=new UserInfo();
            user.setRole(role);
            List<UserInfo>  userInfo = userInfoDao.queryAll(user);
            PageInfo<UserInfo> pageInfo = new PageInfo(userInfo);

            response.setData(pageInfo);

        }catch (Exception e){
            log.error("用户分页查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse insertUser(UserInfo user) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(user!=null) {
                UserInfo userInfo=userInfoDao.queryByUsername(user.getUsername());
                if(userInfo==null) {
                    userInfoDao.insert(user);
                }else if(userInfo!=null){
                    response=new BaseResponse(StatusCode.Repeat);
                    response.setMsg("用户名已存在");
                }
                log.info("新增用户：{} ", user);
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
            if(id!=null) {
                userInfoDao.deleteById(id);
                log.info("删除用户：{} ", id);
            }

        }catch (Exception e){
            log.error("删除用户-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse updateUser(UserInfo user) {BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(user.getId()!=null) {
                UserInfo userInfo= userInfoDao.queryById(user.getId());
                BeanUtills.copyProperties(user, userInfo);
                userInfoDao.update(userInfo);
                log.info("更新用户：{} ", userInfo);
            }

        }catch (Exception e){
            log.error("更新用户-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public UserInfo findUserById(Integer id) {
        UserInfo userInfo=new UserInfo();
        try {
            if(id!=null) {
                userInfo=userInfoDao.queryById(id);
            }

        }catch (Exception e){
            log.error("根据id查询用户信息-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());

        }
        return userInfo;
    }

}