package priv.zsl.utils.aliyun;

//生成六位验证码的类
public class RandomNum {

	public static String getRandom() {
		return (int) ((Math.random() * 9 + 1) * 100000) + "";
	}
}