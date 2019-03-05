package com.dodo.project.base.web.render;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <b>JsRender</b></br>
 *
 * <pre>
 * javaScript渲染器(支持内容和文件输出)
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/25 17:18
 * @Since JDK 1.8
 */
public class JsRender extends Render {
	protected static final String CONTEXT_TYPE = "text/javascript; charset=utf-8";

	protected String text;

	protected File file;

	public JsRender(String context) {
		super(null);
		this.text = context;
	}

	public JsRender(File file) {
		super(null);
		this.file = file;
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

			if (null != text && text.length() != 0) {
				outputStream.write(text.getBytes());
			}

			if (null != file && file.exists()) {
				outputStream.write(FileUtils.readFileToByteArray(file));
			}

			outputStream.flush();

		} catch (IOException e) {
			throw new RuntimeException("output javaScript failed.", e);
		}

	}
}
