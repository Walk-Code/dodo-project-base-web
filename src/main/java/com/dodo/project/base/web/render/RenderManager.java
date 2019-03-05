package com.dodo.project.base.web.render;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>RenderManager</b></br>
 *
 * <pre>
 * renderFa
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/25 10:41
 * @Since JDK 1.8
 */
@Component
public class RenderManager {
	@Autowired
	Environment environment;

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	HttpServletResponse httpServletResponse;

	@Autowired
	IRenderFactory renderFactory;

	/*
	 * @Description: 初始化renderFactory
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: com.dodo.project.base.web.render.IRenderFactory
	 * @Date: 2018/10/25 10:48
	 */
	public IRenderFactory init() {
		renderFactory.init(httpServletRequest, httpServletResponse, environment);

		return renderFactory;
	}

}
