package priv.zsl.utils.crypto;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SimpleHashUtils {

	public static String toMd5Salt(Object username, Object password) {
		String hashAlgorithmName = "MD5";// 加密方式
		int hashIterations = 1024;// 加密1024次
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);// 使用账号作为盐值
		SimpleHash result = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
		return result.toHex();
	}
}
