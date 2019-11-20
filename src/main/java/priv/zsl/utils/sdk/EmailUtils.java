package priv.zsl.utils.sdk;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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
public class EmailUtils {

	@Autowired
	private JavaMailSenderImpl mailSender;

	public static EmailUtils emailUtils;

	@PostConstruct
	public void init() {
		emailUtils = this;
		emailUtils.mailSender = this.mailSender;
	}

	public static void sendEamil(String sendTo, String code) throws MessagingException {
		MimeMessage mimeMessage = emailUtils.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setSubject("【森林郁闭度】邮箱验证");
		helper.setText("<b style='color:red'>" + code + "</b>", true);
		helper.setTo(sendTo);
		helper.setFrom("xm_hhs@163.com");
		
		emailUtils.mailSender.send(mimeMessage);
	}

	public static void sendEamil(String sendTo, String fileName, String filePath) throws MessagingException {
		MimeMessage mimeMessage = emailUtils.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setSubject("【森林郁闭度】邮箱验证");
		helper.setText("<b style='color:red'>请接收附件</b>", true);
		helper.setTo(sendTo);
		helper.setFrom("xm_hhs@163.com");
		helper.addAttachment(fileName, new File(filePath));
		emailUtils.mailSender.send(mimeMessage);
	}
}
