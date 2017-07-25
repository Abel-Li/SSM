package com.hqg.util;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hqg.exception.BizException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LYC on 2017/6/29.
 */
public class StringMVCUtil {

    private static final Log LOG = LogFactory.getLog(StringMVCUtil.class);

    /**
     * 取得当前request
     *
     * @return
     */
    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 根据参数名从HttpRequest中获取Integer类型的参数值，无值则返回null .
     *
     * @param key
     *            .
     * @return IntegerValue or null .
     */
    public static Integer getInteger(String key) {
        String value = getHttpRequest().getParameter(key);
        if (StringUtils.isNotBlank(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    /**
     * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回null .
     *
     * @param key
     *            .
     * @return IntegerValue or null .
     */
    public static String getString(String key) {
        String value = getHttpRequest().getParameter(key);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return null;
    }
    /**
     * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
     *
     * @param key
     *            .
     * @return LongValue or null .
     */
    public static Long getLong(String key) {
        String value = getHttpRequest().getParameter(key);
        if (StringUtils.isNotBlank(value)) {
            return Long.parseLong(value);
        }
        return null;
    }

    /**
     * 根据参数名从HttpRequest中获取Double类型的参数值，无值则返回null .
     *
     * @param key
     *            .
     * @return DoubleValue or null .
     */
    public static Double getDouble(String key) {
        String value = getHttpRequest().getParameter(key);
        if (StringUtils.isNotBlank(value)) {
            return Double.parseDouble(value);
        }
        return null;
    }

    /**
     * 输出JSON，直接输入结果数据
     *
     * @param response
     * @param result
     */
    public static void outWriteJson(HttpServletResponse response, Object result) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            if (result.getClass() == Exception.class) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("code", ((BizException)result).getCode());
                map.put("msg", ((BizException)result).getMsg());
                out.write(getJsonString(map));
            }else{
                out.write(result.toString());
            }
        } catch (IOException e) {
            LOG.error(e);
        } finally {
            out.close();
        }
    }

    /**
     * 将对象转成JSON字符串
     *
     * @param object
     * @return json字符串
     */
    public static String getJsonString(Object object) {
        return com.alibaba.fastjson.JSONObject.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }
    
    /**
	 * 格式化返回内容
	 * 
	 * @param result
	 *            操作结果数据
	 * @return 返回格式化后的结果
	 */
	public static String getResult(Object... result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		map.put("msg", "操作成功");
		map.put("data", result.length != 0 ? result : "");
		return getJsonString(map);
	}
	
	/**
	 * 格式化返回错误内容
	 * 
	 * @param result
	 *            操作结果数据
	 * @return 返回格式化后的结果
	 */
	public static void getErrorResult(HttpServletResponse response, String code,String msg) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			map.put("msg", msg);
			out.write(getJsonString(map));
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			out.close();
		}
	}
}
