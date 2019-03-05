package com.dodo.project.base.web.controller;

import com.dodo.project.base.exception.enums.ResponseStatusEnum;
import com.dodo.project.base.exception.utils.ResponseHelper;

/*
 * <b>AbstractRestController</b></br>
 *
 * <pre>
 *  responess统一处理返回restful风格
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/5 12:06
 * @Since JDK 1.8
 */
public abstract class AbstractRestController {

	/*
	* @Description: 响应成功
	* @Author: walk_code walk_code@163.com
	* @Param: [msg] 
	* @return: void  
	* @Date: 2018/10/5 13:44
	*/ 
	public void responseSuccess(String msg){
		ResponseHelper.response(ResponseStatusEnum.STATUS_200.getStatus(), msg);
	}
	
	/* 
	* @Description: 响应成功
	* @Author: walk_code walk_code@163.com
	* @Param: [object] 
	* @return: void  
	* @Date: 2018/10/12 16:48
	*/ 
	public void responseSuccess(Object object) {
		ResponseHelper.success(object);
	}
	
	/* 
	* @Description: 响应失败
	* @Author: walk_code walk_code@163.com
	* @Param: [msg] 
	* @return: void  
	* @Date: 2018/10/12 16:49
	*/ 
	public void responseFailure(String msg){
		ResponseHelper.failure(msg);
	}
	
	/*
	* @Description: 响应结果
	* @Author: walk_code walk_code@163.com
	* @Param: [status, msg] 
	* @return: void  
	* @Date: 2018/10/12 16:51
	*/ 
	public void response(String status, String msg) {
		ResponseHelper.response(status, msg);
	}

	/*
	* @Description: 响应结果
	* @Author: walk_code walk_code@163.com
	* @Param: [status, msg, object] 
	* @return: void  
	* @Date: 2018/10/12 16:52
	*/ 
	public void response(String status, String msg, Object object) {
		ResponseHelper.response(status, msg, object);
	}
}
