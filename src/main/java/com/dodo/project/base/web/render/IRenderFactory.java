package com.dodo.project.base.web.render;

import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/*
 * @Description: 渲染器工厂接口类
 * @Author: walk_code walk_code@163.com
 * @Param:
 * @return:
 * @Date: 2018/10/24 16:30
 */
public interface IRenderFactory {

	/*
	 * @Description:
	 * @Author: walk_code walk_code@163.com
	 * @Param: [httpServletRequest, httpServletResponse, environment]
	 * @return: void
	 * @Date: 2018/10/24 16:32
	 */
	public void init(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Environment environment);

	/*
	 * @Description: 获取json渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [view]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:34
	 */
	public Render getJsonRender(String view);

	/*
	 * @Description: 获取jsp渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [view]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:36
	 */
	public Render getJspRender(String view);

	/*
	 * @Description: 获取freeMarker渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [view]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:37
	 */
	public Render getFreeMarkerRender(String view);

	/*
	 * @Description: 获取文件渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [file]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:38
	 */
	public Render getFileRender(File file);

	/*
	 * @Description: 获取js文件渲染器
	 * @Author: walk_code walk_code@163.com
	 * @Param: [file]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:42
	 */
	public Render getJsRender(File file);

	/*
	 * @Description: 获取js文件渲染器，内容为content
	 * @Author: walk_code walk_code@163.com
	 * @Param: [content]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:45
	 */
	public Render getJsRender(String content);

	/*
	 * @Description: 获取html文件渲染器，内容为content
	 * @Author: walk_code walk_code@163.com
	 * @Param: [content]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:48
	 */
	public Render getHtmlRender(String content);

	/*
	 * @Description: 获取文档渲染器，内容为content
	 * @Author: walk_code walk_code@163.com
	 * @Param: [content]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:50
	 */
	public Render getTextRender(String content);

	/*
	 * @Description: 获取文档渲染器，内容为content，类型为MIME类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [content, contentType]
	 * @return: com.dodo.project.base.web.render.Render
	 * @Date: 2018/10/24 16:59
	 */
	public Render getTextRender(String content, String contentType);
}
