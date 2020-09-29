package com.example.dubbo.provider.common.utils;


import com.example.dubbo.api.entity.*;
import com.example.dubbo.provider.dao.EquipmentDetailDao;
import com.example.dubbo.provider.dao.TelecontrolDescribeDao;
import com.example.dubbo.provider.dao.UserInfoDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stadpole on 2020/8/12 14:59
 */
@Component
public class StringToObjectUtil {
    @Resource
    private TelecontrolDescribeDao telecontrolDescribeDao;
    @Resource
    private EquipmentDetailDao equipmentDetailDao;
    @Resource
    private UserInfoDao userInfoDao;

    //遥测接收解码
    public Telemetry ConvertToTelemrtry(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strings = s.split(";");
        Telemetry telemetry = new Telemetry();
        telemetry.setTime(format.parse(strings[0]));
        telemetry.setEquipmentId(strings[1]);
        telemetry.setTelemetryName(strings[2]);
        telemetry.setEngineeringValue(strings[3]);
        telemetry.setUnit(strings[4]);
        return telemetry;
    }
    //遥控执行情况返回接收解码  存在日志信息里
    public OperationLog ConvertToTeleControlLog(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strings = s.split(";");
        OperationLog operationLog = new OperationLog();
       if(strings.length==6) {
           operationLog.setTime(format.parse(strings[0]));
           /***
            * 0：地址错误
            * 1：发送成功
            * 2：发送失败
            */

           if(strings[1].equals("0")){
               operationLog.setOperationDetail("地址错误");
           }else if(strings[1].equals("1")){
               operationLog.setOperationDetail("发送成功");
           } else if(strings[1].equals("2")){
               operationLog.setOperationDetail("发送失败");
           }
           operationLog.setMethod("遥控发令");
           operationLog.setUserId(Integer.parseInt(strings[2]));
           operationLog.setUsername(getUserName(Integer.parseInt(strings[2])));
           operationLog.setParam(getEquipmentDetail(strings[3])+getCommandDetail(strings[3],strings[4])+"参数："+strings[5]);
       }
       else if(strings.length==5) {
            operationLog.setTime(format.parse(strings[0]));
            /***
             * 0：地址错误
             * 1：发送成功
             * 2：发送失败
             */

            if(strings[1].equals("0")){
                operationLog.setOperationDetail("地址错误");
            }else if(strings[1].equals("1")){
                operationLog.setOperationDetail("发送成功");
            } else if(strings[1].equals("2")){
                operationLog.setOperationDetail("发送失败");
            }
            operationLog.setMethod("遥控发令");
            operationLog.setUserId(Integer.parseInt(strings[2]));
           operationLog.setUsername(getUserName(Integer.parseInt(strings[2])));
            operationLog.setParam(getEquipmentDetail(strings[3])+getCommandDetail(strings[3],strings[4]));
        }
        return operationLog;
    }
    //前端遥控发送情况返回接收解码  存在日志信息里
    public OperationLog Convert2TeleControlLog(String s) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        String[] strings = s.split(";");
        OperationLog operationLog = new OperationLog();
        String dateTime=df.format(new Date());
        operationLog.setTime(df.parse(dateTime));
        if(strings.length==4) {
            operationLog.setOperationDetail("已发送");
            operationLog.setMethod("遥控发令");
            operationLog.setUserId(Integer.parseInt(strings[0]));
            operationLog.setUsername(getUserName(Integer.parseInt(strings[0])));
            operationLog.setParam(getEquipmentDetail(strings[1])+getCommandDetail(strings[1],strings[2])+"参数："+strings[3]);
        }
        else if(strings.length==3) {
            operationLog.setMethod("遥控发令");
            operationLog.setUserId(Integer.parseInt(strings[0]));
            operationLog.setUsername(getUserName(Integer.parseInt(strings[0])));
            operationLog.setParam(getEquipmentDetail(strings[1])+getCommandDetail(strings[1],strings[2]));
        }
        return operationLog;
    }
    /**
     * 获取设备代号的具体含义
     * @Param equipmentId 设备代号
     *
     */

    public  String  getEquipmentDetail(String equipmentId){
        EquipmentDetail equipmentDetail= equipmentDetailDao.queryById(equipmentId);
        String name=equipmentDetail.getStationName()+" "+equipmentDetail.getEquipmentName()+" ";
        return name;
    }
    /**
     * 获取指令代号的具体含义
     * @Param equipmentId 设备代号
     * @Param commandNumber 指令代号
     */
    public  String  getCommandDetail(String equipmentId,String commandNumber){
        TelecontrolDescribe telecontrolDescribe= telecontrolDescribeDao.queryById(equipmentId,commandNumber);
        String name=telecontrolDescribe.getDescribeInfo()+" ";
        return name;
    }
    /**
     * 根据用户id获取用户名
     * @Param userId 用户id
     */
    public  String  getUserName(Integer userId){
        UserInfo userInfo= userInfoDao.queryById(userId);
        String username=userInfo.getUsername();
        return username;
    }
}
