package com.dodo.project.base.web.render;

import com.dodo.project.base.web.utils.JsonHelper;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * <b>JsonRender</b></br>
 *
 * <pre>
 * json渲染器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/24 17:47
 * @Since JDK 1.8
 */
public class JsonRender extends Render {

	public JsonRender(String view) {
		super(view);
	}

	@Override
	public Render initData() {
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setHeader("Cache-Control", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("application/json; charset=UTF-8");

		return this;
	}

	@Override
	public void render() {
		String jsonText = buildJsonText();
		try (PrintWriter writer = httpServletResponse.getWriter()) {
			writer.write(jsonText);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * @Description: 生成json字符串
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/24 18:18
	 */
	private String buildJsonText() {
		return JsonHelper.toJson(getAttrs());
	}

}
