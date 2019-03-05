package com.dodo.project.base.web.render;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <b>TextRender</b></br>
 *
 * <pre>
 * 文本渲染器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/25 17:48
 * @Since JDK 1.8
 */
public class TextRender extends Render {
	protected static String CONTEXT_TYPE = "text/plain; charset=utf-8";

	protected String text;

	public TextRender(String context) {
		super(null);
		this.text = context;
	}

	public TextRender(String context, String contextType) {
		super(null);
		if (null != context) {
			this.CONTEXT_TYPE = contextType;
		}
	}

	@Override
	public Render initData() {
		return this;
	}

	@Override
	public void render() {
		// TODO 需要抽离优化
		try (OutputStream outputStream = httpServletResponse.getOutputStream()) {
			httpServletResponse.reset();
			httpServletResponse.setHeader("Pragma", "no-cache");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			httpServletResponse.setDateHeader("Expires", 0);
			httpServletResponse.setContentType(CONTEXT_TYPE);

			outputStream.write(text.getBytes());
			outputStream.flush(); // RepeatReadRequestWrapper

		} catch (IOException e) {
			throw new RuntimeException("output text failed.", e);
		}
	}
}
