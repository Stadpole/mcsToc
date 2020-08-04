package com.example.dubbo.api.common.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Data
@ToString
public class OperationLogRequest implements Serializable {
    private Integer id;
    private String username;//用户名
    private String operation;//操作
    private String method;//方法
    private String params;//参数
    private String ip;//IP
    private Date time;//时间
}