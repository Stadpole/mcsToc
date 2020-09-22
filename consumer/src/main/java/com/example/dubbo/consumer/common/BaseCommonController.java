package com.example.dubbo.consumer.common;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: author: LiPeng
 **/

public class BaseCommonController {
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";//"yyyy-MM-dd HH:mm:ss";

	public  String sendSuccessMessage() {
		return sendMessage("true", "SUCCESS", null);
	}

	public  String sendSuccessMessage(Object data) {
		return sendMessage("true", "SUCCESS", data);
	}
	public  String sendSuccessMessage(String message) {
		return sendMessage("true", message, null);
	}
	public  String sendSuccessMessage(String message,Object data) {
		return sendMessage("true", message, data);
	}

	public  String sendFailMessage() {
		return sendMessage("false", "FAILED", null);
	}
	public  String sendFailMessage(Object data) {
		return sendMessage("false", "FAILED", data);
	}
	public  String sendFailMessage(String message) {
		return sendMessage("false", message, null);
	}
	public  String sendFailMessage(String message,Object data) {
		return sendMessage("false", message, data);
	}
	
	public  String sendMessage(String status,String message,Object data){
		Gson gson=new Gson();
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		result.put("message", message);
		result.put("data", data);
		return gson.toJson(result);
		//return  result.toString();
	}
	public  String sendLoginMessage(String status,String uid,String role){
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		result.put("uid", uid);
		result.put("role", role);
		return Object2Json(result,DATE_TIME);
		//return  result.toString();
	}
	public  String send(List list){
		Map<String, Object> result = new HashMap<>();
		result.put("option",list);
		return Object2Json(result,DATE_TIME);
		//return  result.toString();
	}
	public  String sendMessage(String status,String message,Object data,String dateFormat){
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		result.put("message", message);
		result.put("data", data);
		if (null == dateFormat || "".equals(dateFormat)){
			return Object2Json(result,DATE_TIME);
		}else {
			return Object2Json(result,dateFormat);
		}
	}


	private static String Object2Json(Object obj, String dateFormat) {
		Gson gson = null;
		if (null != dateFormat) {
			gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(dateFormat).create();
		} else {
			gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		}
		try {
			if (null != gson && null != obj) {
				return gson.toJson(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
