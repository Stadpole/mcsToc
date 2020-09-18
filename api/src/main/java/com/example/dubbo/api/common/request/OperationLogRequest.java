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
    private Integer user_id;//用户名
    private String operation_detail;//操作详情
    private String method;//方法
    private String param;//参数
    private Date time;//时间
}