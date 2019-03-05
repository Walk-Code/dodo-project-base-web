package com.dodo.project.base.web.render;

import com.dodo.project.base.web.constants.BaseWebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * <b>JspRender</b></br>
 *
 * <pre>
 * jsp渲染器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/25 13:53
 * @Since JDK 1.8
 */
public class JspRender extends Render {
	private static final Logger logger = LoggerFactory.getLogger(JspRender.class);

	public JspRender(String view) {
		super(view);
	}

	@Override
	public Render initData() {
		// 初始化JSP
		logger.info("获取配置文件中视图的配置："+BaseWebConstants.RENDER_JSP_VIEW_PATH);
		viewPath = env.getProperty(BaseWebConstants.RENDER_JSP_VIEW_PATH);
		suffix = env.getProperty(BaseWebConstants.RENDER_JSP_VIEW_SUFFIX);

		return this;
	}

	@Override
	public void render() {
		try {
			httpServletRequest.getRequestDispatcher(view).forward(httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			throw new RuntimeException("生成jsp视图失败。", e);
		}
	}
}
