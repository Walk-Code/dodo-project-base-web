package com.dodo.project.base.web.render;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <b>Render</b></br>
 *
 * <pre>
 *  渲染器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/24 14:35
 * @Since JDK 1.8
 */
public abstract class Render {
	// 视图路径
	protected String viewPath;

	// 后缀
	protected String suffix;

	// 视图
	protected String view;

	// servletRequest
	protected HttpServletRequest httpServletRequest;

	// servletResponse
	protected HttpServletResponse httpServletResponse;

	// 环境
	protected Environment env;

	public Render(String view) {
		this.view = view;
	}

	/*
	 * @Description: 过滤程序隐式加入的数据
	 * @Author: walk_code walk_code@163.com
	 * @Param:
	 * @return:
	 * @Date: 2018/10/24 19:25
	 */
	protected static final Set<String> excludedAttrs = new HashSet<String>() {
		{
			// TODO 需要过滤的类，示例：org.springframework.web.context.request.async.WebAsyncManager.WEB_ASYNC_MANAGER
		}
	};

	/*
	 * @Description: 返回当前渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [request, response]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 15:59
	 */
	protected Render setContext(HttpServletRequest request, HttpServletResponse response) {
		this.httpServletRequest = request;
		this.httpServletResponse = response;

		return this;
	}

	/*
	 * @Description: 返回当前渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [request, response, env]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:08
	 */
	protected Render setContext(HttpServletRequest request, HttpServletResponse response, Environment env) {
		this.httpServletRequest = request;
		this.httpServletResponse = response;
		this.env = env;

		return this;
	}

	/*
	 * @Description: 获取用户显式调用setAttr加入的数据
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.util.Map
	 * @Date: 2018/10/24 19:35
	 */
	protected Map getAttrs() {
		Map                 map   = new HashMap();
		Enumeration<String> attrs = httpServletRequest.getAttributeNames();
		while (attrs.hasMoreElements()) {
			String key = attrs.nextElement();
			if (excludedAttrs.contains(key)) {
				continue;
			}

			Object object = httpServletRequest.getAttribute(key);
			map.put(key, object);
		}

		return map;
	}


	/*
	 * @Description: 初始化数据
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:08
	 */
	public abstract Render initData();

	/*
	 * @Description: 渲染视图(视图的路径)
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:22
	 */
	protected Render decorateView() {
		if (null != view && view.length() > 0 && view.charAt(0) != '/') {
			view = viewPath + view + suffix;
		}

		return this;
	}

	/*
	 * @Description: 自定义渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: void
	 * @Date: 2018/10/24 19:39
	 */
	public abstract void render();

}
