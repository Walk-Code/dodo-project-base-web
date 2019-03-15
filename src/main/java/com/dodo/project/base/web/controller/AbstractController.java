package com.dodo.project.base.web.controller;

import com.dodo.project.base.web.render.RenderManager;
import com.dodo.project.base.web.utils.JsonHelper;
import com.dodo.project.base.web.utils.TypeTransformHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * <b>AbstractController</b></br>
 *
 * <pre>
 *  基础控制器
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/5 12:06
 * @Since JDK 1.8
 */
@Controller
public abstract class AbstractController extends AbstractRestController {

	public final static Logger logger = LoggerFactory.getLogger(AbstractController.class);

	//全局注入HttpServletRequest & HttpServletResponse
	@Autowired
	protected HttpServletRequest httpServletRequest;

	@Autowired
	protected HttpServletResponse httpServletResponse;

	@Autowired
	private RenderManager renderManager;

	/*
	 * @Description: 获取客户端ip
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 20:22
	 */
	public String getIp() {
		return httpServletRequest.getRemoteAddr();
	}

	/*
	 * @Description: 获取user-agent（UA）
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:03
	 */
	public String getUserAgent() {
		return httpServletRequest.getHeader("user-agent");
	}

	/*
	 * @Description: 获取host
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:03
	 */
	public String getHost() {
		return httpServletRequest.getHeader("Host");
	}

	/*
	 * @Description: 获取 Accept-Encoding
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:04
	 */
	public String getAcceptEncoding() {
		return httpServletRequest.getHeader("Accept-Encoding");
	}

	/*
	 * @Description: 获取X-Forwarded-For（HTTP 扩展头部）
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:07
	 */
	public String getXForwardedFor() {
		return httpServletRequest.getHeader("X-Forwarded-For");
	}

	/*
	 * @Description: 获取X-Forwarded-Proto
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:14
	 */
	public String getXForwardedProto() {
		return httpServletRequest.getHeader("X-Forwarded-Proto");
	}

	/*
	 * @Description: 获取X-Request-Start(请求时间)
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:21
	 */
	public String getXRequestStart() {
		return httpServletRequest.getHeader("X-Request-Start");
	}

	/*
	 * @Description: 获取Accept(获取客户端处理MIME类型)
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:24
	 */
	public String getAccept() {
		return httpServletRequest.getHeader("Accept");
	}

	/*
	 * @Description: 获取客户端持久链接状态
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/17 21:30
	 */
	public String getConnection() {
		return httpServletRequest.getHeader("Connection");
	}

	/*
	 * @Description: 获取请求的端口
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/18 18:11
	 */
	public String getXForwardedPort() {
		return httpServletRequest.getHeader("X-Forwarded-Port");
	}

	/*
	 * @Description: 往request添加参数
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, value]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/18 18:32
	 */
	public AbstractController setAttr(String name, Object value) {
		httpServletRequest.setAttribute(name, value);

		return this;
	}

	/*
	 * @Description: 移除request中的参数
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/18 18:32
	 */
	public AbstractController removeAttr(String name) {
		httpServletRequest.removeAttribute(name);

		return this;
	}


	/*
	 * @Description: 往request里添加多参数
	 * @Author: walk_code walk_code@163.com
	 * @Param: [map]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/18 18:35
	 */
	public AbstractController setAttrs(Map<String, Object> map) {
		map.forEach((k, v) -> {
			httpServletRequest.setAttribute(k, v);
		});

		return this;
	}

	/*
	 * @Description: 获取request参数
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/18 18:55
	 */
	public String getParameter(String paramName) {
		// 获取表单的参数
		String paramValue = httpServletRequest.getParameter(paramName);
		if (StringUtils.isNotBlank(paramValue)) {
			return paramValue;
		}

		String payloadStr = getRequestPayload(httpServletRequest);
		if (StringUtils.isBlank(payloadStr)) {
			return null;
		}

		String contentType = httpServletRequest.getContentType();
		if (contentType.contains("application/json")) {
			Map<String, Object> requestParamMap = JsonHelper.parse(payloadStr, Map.class);

			if (null != requestParamMap.get(paramName)) {
				return String.valueOf(requestParamMap.get(paramName));
			} else {
				return null;
			}
		}

		return null;
	}

	/*
	 * @Description: 通过流获取request body里面的内容
	 * @Author: walk_code walk_code@163.com
	 * @Param: [request]
	 * @return: java.lang.String
	 * @Date: 2018/10/19 10:01
	 */
	public String getRequestPayload(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();

		try {
			BufferedReader reader = request.getReader();
			char[]         buff   = new char[1024];
			int            len    = reader.read(buff);
			while (len != -1) {
				stringBuilder.append(buff, 0, len);
			}

		} catch (IOException e) {
			// TODO 添加日志处理
			e.printStackTrace();
			logger.error("Reader is read in the request error.", e);
		}

		return stringBuilder.toString();
	}

	/*
	 * @Description: 获取request参数，当参数为空设置默认值
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, defaultValue]
	 * @return: java.lang.String
	 * @Date: 2018/10/20 11:16
	 */
	public String getParameter(String name, String defaultValue) {
		String result = getParameter(name);

		if (null != result && !"".equals(result)) {
			return result;
		} else {
			return defaultValue;
		}
	}

	/*
	 * @Description: 获取request参数，转成int类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Integer
	 * @Date: 2018/10/20 11:19
	 */
	public Integer getParameterToInt(String name) {
		return getParameterToInt(getParameter(name), 0);
	}

	/*
	 * @Description: 获取request参数，转成int类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Integer
	 * @Date: 2018/10/20 11:19
	 */
	public Integer getParameterToInt(String name, Integer defaultValue) {
		return TypeTransformHelper.objToInt(getParameter(name), defaultValue);
	}

	/*
	 * @Description: 获取request参数，转成long类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, defaultValue]
	 * @return: java.lang.Long
	 * @Date: 2018/10/20 19:20
	 */
	public Long getParamterToLong(String name, Long defaultValue) {
		return TypeTransformHelper.objToLong(getParameter(name), defaultValue);
	}

	/*
	 * @Description: 获取request参数，转成long类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Long
	 * @Date: 2018/10/20 19:18
	 */
	public Long getParamterToLong(String name) {
		return getParamterToLong(name, 0L);
	}

	public Boolean getParameterToBoolean(String name, Boolean defaultValue) {
		return TypeTransformHelper.objToBoolean(getParameter(name), defaultValue);
	}

	/*
	 * @Description: 获取request参数，转成Date类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, defaultValue]
	 * @return: java.util.Date
	 * @Date: 2018/10/20 19:38
	 */
	public Date getParameterToDate(String name, Date defaultValue) {
		return TypeTransformHelper.objToDate(getParameter(name), defaultValue);
	}

	/*
	 * @Description: 获取request参数，转成Date类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.util.Date
	 * @Date: 2018/10/20 19:39
	 */
	public Date getParameterToDate(String name) {
		return getParameterToDate(getParameter(name), null);
	}

	/*
	 * @Description: 获取request参数，转成double类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: double
	 * @Date: 2018/10/22 19:27
	 */
	public double getParameterToDoulbe(String name) {
		return getParameterToDouble(name, 0);
	}

	/*
	 * @Description: 获取request参数，转成double类型
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, defalutValue]
	 * @return: double
	 * @Date: 2018/10/22 20:16
	 */
	public double getParameterToDouble(String name, double defalutValue) {
		return TypeTransformHelper.objToDouble(getParameter(name), defalutValue);
	}

	/*
	 * @Description: 获取request参数，转成map
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.util.Map<java.lang.String,java.lang.String[]>
	 * @Date: 2018/10/25 20:31
	 */
	public Map<String, String[]> getParaMap() {
		return httpServletRequest.getParameterMap();
	}

	/*
	 * @Description: 获取request body参数
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/10/22 20:20
	 */
	public String getAllParameterToStr() {
		// 获取request body的参数
		String payloadStr = getRequestPayload(httpServletRequest);

		return payloadStr;
	}

	/*
	 * @Description: 获取request getAttribute的参数
	 * @Author:walk_code walk_code@163.com
	 * @Param:[name]
	 * @return: T
	 * @Date:2018/10/23 14:01
	 */
	public <T> T getAttr(String name) {
		return (T) httpServletRequest.getAttribute(name);
	}


	/*
	 * @Description: 获取request
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: javax.servlet.http.HttpServletRequest
	 * @Date: 2018/10/23 14:33
	 */
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	/*
	 * @Description: 获取response
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: javax.servlet.http.HttpServletResponse
	 * @Date: 2018/10/23 14:33
	 */
	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	/*
	 * @Description: 获取httpSession
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: javax.servlet.http.HttpSession
	 * @Date: 2018/10/23 14:34
	 */
	public HttpSession getSession() {
		return getSession(false);
	}

	/*
	 * @Description:
	 * @Author: walk_code walk_code@163.com
	 * @Param: [create]
	 * @return: javax.servlet.http.HttpSession
	 * @Date: 2018/10/23 14:53
	 */
	public HttpSession getSession(boolean create) {
		return httpServletRequest.getSession(create);
	}


	/*
	 * @Description: 获取httpSession内容
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: T
	 * @Date: 2018/10/23 15:02
	 */
	public <T> T getSessionAttribute(String name) {
		HttpSession session = getSession(false);

		return (session != null ? (T) session.getAttribute(name) : null);
	}

	/*
	 * @Description: 设置seeion内容
	 * @Author: walk_code walk_code@163.com
	 * @Param: [key, value]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/23 15:19
	 */
	public AbstractController setSessionAttribute(String key, Object value) {
		httpServletRequest.getSession(true).setAttribute(key, value);

		return this;
	}

	/*
	 * @Description: 移除session
	 * @Author: walk_code walk_code@163.com
	 * @Param: [key]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/23 15:25
	 */
	public AbstractController removeSessionAttribute(String key) {
		HttpSession session = getSession(false);
		if (null != session) {
			session.removeAttribute(key);
		}

		return this;
	}

	/*
	 * @Description: 获取cookie值,转String
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.String
	 * @Date: 2018/10/23 15:27
	 */
	public String getCookie(String name) {
		return getCookie(name, null);
	}


	/*
	 * @Description: 获取cookie,转String
	 * @Author: walk_code walk_code@163.com
	 * @Param: [key, defaultValue]
	 * @return: java.lang.String
	 * @Date: 2018/10/23 15:28
	 */
	public String getCookie(String key, String defaultValue) {
		Cookie cookie = getCookieObject(key);

		return (null != cookie ? cookie.getValue() : defaultValue);
	}

	/*
	 * @Description: 获取Cookie对象
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: javax.servlet.http.Cookie
	 * @Date: 2018/10/23 15:31
	 */
	public Cookie getCookieObject(String name) {
		Cookie[] cookies = httpServletRequest.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}

		return null;
	}

	/*
	 * @Description: 获取cookie,转int
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Integer
	 * @Date: 2018/10/23 15:41
	 */
	public Integer getCookieToInt(String name) {
		return getCookieToInt(name, null);
	}

	/*
	 * @Description: 获取cookie,转int
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, defaultValue]
	 * @return: java.lang.Integer
	 * @Date: 2018/10/23 15:42
	 */
	public Integer getCookieToInt(String name, Integer defaultValue) {
		return TypeTransformHelper.objToInt(getCookie(name), defaultValue);
	}


	/*
	 * @Description: 获取cookie,转long
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Long
	 * @Date: 2018/10/23 15:43
	 */
	public Long getCookieToLong(String name) {
		return getCookieToLong(name, null);
	}

	/*
	 * @Description: 获取cookie,转long
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Long
	 * @Date: 2018/10/23 15:43
	 */
	public Long getCookieToLong(String name, Long defaultValue) {
		return TypeTransformHelper.objToLong(getCookie(name), defaultValue);
	}

	/*
	 * @Description: 获取cookie,转Boolean
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.Long
	 * @Date: 2018/10/23 15:43
	 */
	public boolean getCookieToBoolean(String name) {
		return getCookieToBoolean(name, false);
	}

	/*
	 * @Description: 获取cookie,转Boolean
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, defaultValue]
	 * @return: java.lang.Long
	 * @Date: 2018/10/23 15:43
	 */
	public boolean getCookieToBoolean(String name, boolean defaultValue) {
		return TypeTransformHelper.objToBoolean(getCookie(name), defaultValue);
	}

	/*
	 * @Description: 设置cookie
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, value, maxAagInSeconds, path, isHttpOnly]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/24 10:12
	 */
	public AbstractController setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
		return doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
	}

	/*
	 * @Description: 移除cookie
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/24 10:39
	 */
	public AbstractController removeCookie(String name) {
		return doSetCookie(name, null, 0, null, null, null);
	}

	/*
	 * @Description: 移除cookie
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, path]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/24 10:53
	 */
	public AbstractController removeCookie(String name, String path) {
		return doSetCookie(name, null, 0, path, null, null);
	}

	/*
	 * @Description: 移除cookie
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, path, domain]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/24 11:03
	 */
	public AbstractController removeCookie(String name, String path, String domain) {
		return doSetCookie(name, null, 0, path, domain, null);
	}

	/*
	 * @Description: 添加一个新cookie
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, value, maxAgeInSeconds, path, domain, isHttpOnly]
	 * @return: com.dodo.project.base.web.controller.AbstractController
	 * @Date: 2018/10/24 10:31
	 */
	public AbstractController doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAgeInSeconds); // 失效时间

		if (null == path) {
			path = "/";
		}

		cookie.setPath(path);

		if (null != domain) {
			cookie.setDomain(domain);
		}

		if (null != isHttpOnly) {
			cookie.setHttpOnly(isHttpOnly);
		}

		httpServletResponse.addCookie(cookie);

		return this;
	}

	/*
	 * @Description: 获取header
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name]
	 * @return: java.lang.String
	 * @Date: 2018/10/24 11:06
	 */
	public String getheader(String name) {
		return httpServletRequest.getHeader(name);
	}

	/*
	 * @Description: 设置header
	 * @Author: walk_code walk_code@163.com
	 * @Param: [name, value]
	 * @return: java.lang.String
	 * @Date: 2018/10/24 11:07
	 */
	public void setHeader(String name, String value) {
		httpServletResponse.setHeader(name, value);
	}

	/*
	 * @Description: 获取多文件上传信息
	 * @Author: walk_code walk_code@163.com
	 * @Param: [fileName]
	 * @return: java.util.List<org.springframework.web.multipart.MultipartFile>
	 * @Date: 2018/10/24 11:25
	 */
	public List<MultipartFile> getUploadFileList(String fileName) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
		List<MultipartFile>         files        = multiRequest.getFiles(fileName);

		return files;
	}

	/*
	 * @Description: 获取单文件上传信息
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: org.springframework.web.multipart.MultipartFile
	 * @Date: 2018/10/24 11:29
	 */
	public MultipartFile getUploadMultipartFile(String name) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
		MultipartFile               file         = multiRequest.getFile(name);

		return file;
	}

	/*
	 * @Description: 获取文件流
	 * @Author: walk_code walk_code@163.com
	 * @Param: [fileName]
	 * @return: java.io.InputStream
	 * @Date: 2018/10/24 11:33
	 */
	public InputStream getUploadFileInputStream(String fileName) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
		MultipartFile               file         = multiRequest.getFile(fileName);

		if (null == multiRequest) {
			return null;
		}

		InputStream inputStream = null;

		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取[" + fileName + "] 文件流失败。", e);
		}

		return inputStream;
	}

	/*
	 * @Description: 输出json
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: void
	 * @Date: 2018/10/25 10:17
	 */
	public void renderJson() {
		renderManager.init().getJsonRender(null).render();
	}

	/*
	 * @Description: 输出jsp模板
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: void
	 * @Date: 2018/10/25 20:13
	 */
	public void renderJsp(String view) {
		// 处理
		if ("" != view) {
			view = view.startsWith("/") ? view.substring(1, view.length()) : view;
		}

		renderManager.init().getJspRender(view).render();
	}

	/*
	 * @Description: 输入文件
	 * @Author: walk_code walk_code@163.com
	 * @Param: [file]
	 * @return: void
	 * @Date: 2018/10/25 20:21
	 */
	public void renderFile(File file) {
		renderManager.init().getFileRender(file).render();
	}

	/*
	 * @Description: 输出js文件
	 * @Author: walk_code walk_code@163.com
	 * @Param: [file]
	 * @return: void
	 * @Date: 2018/10/25 20:22
	 */
	public void renderJs(File file) {
		renderManager.init().getJsRender(file).render();
	}

	/*
	 * @Description: 输出js文件
	 * @Author: walk_code walk_code@163.com
	 * @Param: [content]
	 * @return: void
	 * @Date: 2018/10/25 20:25
	 */
	public void renderJs(String content) {
		renderManager.init().getJsRender(content).render();
	}

	/*
	 * @Description: 输出freeMarker模板
	 * @Author: walk_code walk_code@163.com
	 * @Param: [view]
	 * @return: void
	 * @Date: 2018/10/25 20:26
	 */
	public void renderFreeMarker(String view) {
		renderManager.init().getFreeMarkerRender(view).render();
	}

	/*
	 * @Description: 输出html文件
	 * @Author: walk_code walk_code@163.com
	 * @Param: [content]
	 * @return: void
	 * @Date: 2018/10/25 20:27
	 */
	public void renderHtml(String content) {
		renderManager.init().getHtmlRender(content).render();
	}

	/*
	 * @Description: 输入文本
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: void
	 * @Date: 2018/10/25 20:28
	 */
	public void renderText(String content) {
		renderManager.init().getTextRender(content).render();
	}

	/*
	 * @Description: 输出文本
	 * @Author: walk_code walk_code@163.com
	 * @Param: [context, contentTye(mime类型)]
	 * @return: void
	 * @Date: 2018/10/25 20:30
	 */
	public void renderText(String context, String contentTye) {
		renderManager.init().getTextRender(context, contentTye);
	}

	/*
	 * @Description: 301重定向
	 * @Author: walk_code walk_code@163.com
	 * @Param: [targetUrl]
	 * @return: void
	 * @Date: 2018/10/25 20:40
	 */
	public void redirect301(String targetUrl) {
		redirect(targetUrl, 301);
	}

	/*
	 * @Description: 302重定向
	 * @Author: walk_code walk_code@163.com
	 * @Param: [targetUrl]
	 * @return: void
	 * @Date: 2018/10/25 20:41
	 */
	public void redirect302(String targetUrl) {
		redirect(targetUrl, 302);
	}

	/*
	 * @Description: 重定向
	 * @Author: walk_code walk_code@163.com
	 * @Param: [targetUrl, status]
	 * @return: void
	 * @Date: 2018/10/25 20:43
	 */
	public void redirect(String targetUrl, int status) {
		setHeader("Location", targetUrl);
		httpServletResponse.setStatus(status);
	}

	/*
	 * @Description: 校验请求是否为ajax
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: boolean
	 * @Date: 2018/10/26 12:01
	 */
	public boolean isAjax() {
		String requestWithHeader = httpServletRequest.getHeader("X-Requested-With");

		return "XMLHttpRequest".equals(requestWithHeader);
	}
}
