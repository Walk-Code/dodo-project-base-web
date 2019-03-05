package com.dodo.project.base.web.render;

import com.dodo.project.base.web.constants.BaseWebConstants;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * <b>FreeMarkerRender</b></br>
 *
 * <pre>
 *  freeMarker 渲染器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/25 14:45
 * @Since JDK 1.8
 */
public class FreeMarkerRender extends Render {
	public FreeMarkerRender(String view) {
		super(view);
	}

	@Override
	public Render initData() {
		viewPath = env.getProperty(BaseWebConstants.RENDER_FREEMARKER_VIEW_PATH);
		suffix = env.getProperty(BaseWebConstants.RENDER_FREEMARKER_SUFFIX);

		return this;
	}

	@Override
	public void render() {
		try {
			httpServletRequest.getRequestDispatcher(view).forward(httpServletRequest, httpServletResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
