package priv.zsl.utils.jackson;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @Title: JsonUtils.java
 * @Package com.lee.utils
 * @Description: 自定义响应结构, 转换类 Copyright: Copyright (c) 2016
 *               Company:Nathan.Lee.Salvatore
 * @author xmhhs
 *
 */
public class JsonUtils {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	// 响应业务状态
	private Integer status;
	// 响应消息
	private String msg;
	// 响应中的数据
	private Object data;

	
	
	public static JsonUtils judgeOE(boolean flag, String msg, Object data) {
		return flag ? ok(data) : error(msg, data);
	}

	public static JsonUtils judgeE(boolean flag, String msg, Object data) {
		return flag ? ok() : error(msg, data);
	}

	public static JsonUtils judgeO(boolean flag, String msg, Object data) {
		return flag ? ok(data) : error(msg);
	}

	public static JsonUtils judge(boolean flag, String msg) {
		return flag ? ok() : error(msg);
	}

	public static JsonUtils judge(boolean flag) {
		return flag ? ok() : error();
	}

	public static JsonUtils ok() {
		return new JsonUtils(null);
	}

	public static JsonUtils ok(Object data) {
		return new JsonUtils(data);
	}

	public static JsonUtils error() {
		return new JsonUtils(500, null, null);
	}

	public static JsonUtils error(String msg) {
		return new JsonUtils(500, msg, null);
	}

	public static JsonUtils error(String msg, Object data) {
		return new JsonUtils(500, msg, data);
	}

	public JsonUtils(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public JsonUtils(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将对象转换成json字符串。
	 * <p>
	 * Title: pojoToJson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>
	 * Title: jsonToList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
