package com.dodo.project.base.web.render;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * <b>RenderFactory</b></br>
 *
 * <pre>
 * 渲染器工厂实现类
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/24 17:01
 * @Since JDK 1.8
 */
@Component
public class RenderFactory implements IRenderFactory {

	private Environment environment;

	private HttpServletRequest httpServletRequest;

	private HttpServletResponse httpServletResponse;

	@Override
	public void init(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Environment environment) {
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
		this.environment = environment;
	}

	@Override
	public Render getJsonRender(String view) {
		return new JsonRender(view).setContext(httpServletRequest, httpServletResponse).initData();
	}

	@Override
	public Render getJspRender(String view) {
		return new JspRender(view).setContext(httpServletRequest, httpServletResponse, environment).initData().decorateView();
	}

	@Override
	public Render getFreeMarkerRender(String view) {
		return new FreeMarkerRender(view).setContext(httpServletRequest, httpServletResponse, environment).initData().decorateView();
	}

	@Override
	public Render getFileRender(File file) {
		return new FileRender(file).setContext(httpServletRequest, httpServletResponse).initData();
	}

	@Override
	public Render getJsRender(File file) {
		return new JsRender(file).setContext(httpServletRequest, httpServletResponse).initData();
	}

	@Override
	public Render getJsRender(String content) {
		return new JsRender(content).setContext(httpServletRequest, httpServletResponse).initData();
	}

	@Override
	public Render getHtmlRender(String content) {
		return new HtmlRender(content).setContext(httpServletRequest, httpServletResponse).initData();
	}

	@Override
	public Render getTextRender(String content) {
		return new TextRender(content).setContext(httpServletRequest, httpServletResponse).initData();
	}

	@Override
	public Render getTextRender(String content, String contextType) {
		return new TextRender(content, contextType).setContext(httpServletRequest, httpServletResponse).initData();
	}
}
