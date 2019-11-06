package priv.zsl.utils.sdk;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import io.goeasy.GoEasy;

/**
 * 
 *
 * @author xmhhs
 * @Description: TODO
 * @date 2018年7月19日
 * @version V1.0
 *
 */
@Component
public class GoeasyUtils {
	private String appKey="BC-767b451b177e4eb3934d3f5014ceed2d";
	private String RestHost="rest-hangzhou.goeasy.io";
	
	private static GoEasy goEasy;

	public static GoeasyUtils goeasyUtils;

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
		goeasyUtils = this;
		goeasyUtils.goEasy = new GoEasy( "https://"+RestHost, appKey);
		System.out.println("初始化:"+goeasyUtils.goEasy);

	}

	public static void publish(String channel, String content) {
		goeasyUtils.goEasy.publish(channel,content);
	}
	public static void publishAll(String content) {
		goeasyUtils.goEasy.publish("all",content);
	}
	
}
