package com.dodo.project.base.web.render;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <b>FileRender</b></br>
 *
 * <pre>
 * 文件渲染器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/25 14:53
 * @Since JDK 1.8
 */
public class FileRender extends Render {

	protected File file;

	protected String baseDownloadPath;

	public FileRender(File file) {
		super(null);
		if (null == file) {
			throw new IllegalArgumentException("file cannot be empty.");
		}

		this.file = file;
	}

	public FileRender(String fileName) {
		super(null);
		if (null == fileName || fileName.length() == 0) {
			throw new IllegalArgumentException("file path cannot be empty.");
		}

		// 全文件路径
		String fullFileName;
		fileName = fileName.trim();

		if (fileName.startsWith("/") || fileName.startsWith("\\")) {
			// TODO 待完善
		}
	}


	@Override
	public Render initData() {
		return this;
	}

	@Override
	public void render() {
		if (null == file || !file.isFile()) {
			throw new RuntimeException("file does not exist or file is invalid.");
		}

		try (OutputStream outputStream = httpServletResponse.getOutputStream()) {
			httpServletResponse.reset();
			httpServletResponse.setContentType("application/octet-stream; charset=utf-8");
			String headerValue = String.format("attachment;  filename=\"%s\"", file.getName());
			httpServletResponse.setHeader("Content-Disposition", headerValue);

			outputStream.write(FileUtils.readFileToByteArray(file));
			outputStream.flush();

		} catch (IOException e) {
			throw new RuntimeException("the output file failed.", e);
		}

	}
}
