package priv.zsl.utils.sdk;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author smzsl
 * @Description: TODO
 * @date 2019年7月19日
 * @version V1.0
 *
 */

@Component
public class RedisUtils {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	public static RedisUtils redisUtils;

	/*
	 * @PostConstruct是Java
	 * EE5规范之后，Servlet新增的两个影响servlet声明周期的注解之一，另外一个是@PreConstruct。
	 * 这两个都可以用来修饰一个非静态的返回值为void的方法，并且该方法不能抛出异常。
	 * 被@PostConstruct注解修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet中的init方法。
	 * 被该注解修饰的方法会在构造器执行之后，init方法执行之前执行。Spring中允许开发者在受理的Bean中去使用它，当IOC容器被实例化管理当前bean时
	 * ，被该注解修饰的方法会执行，完成一些初始化的工作。
	 * 被@PreConstruct注解修饰的方法会在服务器卸载Servlet的时候运行，类似于Servlet中的destroy方法。
	 * 被该注解修饰的方法会在destroy方法执行之后，Servlet彻底卸载之前执行。
	 */
	@PostConstruct
	public void init() {
		redisUtils = this;
		redisUtils.redisTemplate = this.redisTemplate;
	}

	public static void set(Object key, Object value) {
		redisUtils.redisTemplate.opsForValue().set(key, value);
	}

	public static void set(Object key, Object value,long timeout) {
		redisUtils.redisTemplate.opsForValue().set(key, value,timeout,TimeUnit.SECONDS);
	}

	
	public static Object get(Object key) {
		return redisUtils.redisTemplate.opsForValue().get(key);
	}

	public static void delete(Object key) {
		redisUtils.redisTemplate.delete(key);
	}

}
